package hadoop.排序;

import hadoop.arvo.NcdcRecordParser;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class MaxRemperatureUsingSecondSort extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new MaxRemperatureUsingSecondSort(), args);
        System.exit(run);
    }

    @Override
    public int run(String[] strings) throws Exception {

        Job job = new Job();
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setPartitionerClass(FirstPartitioner.class);
        job.setSortComparatorClass(KeyComparator.class);
        job.setGroupingComparatorClass(GroupComparator.class);
        job.setReducerClass(MaxTemperatureReducer.class);
        job.setOutputKeyClass(IntPair.class);
        job.setOutputValueClass(NullWritable.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static class MaxTemperatureMapper extends Mapper<LongWritable, Text, IntPair, NullWritable> {
        private NcdcRecordParser parser = new NcdcRecordParser();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            parser.parse(value);
            if (parser.isValidTemperature()) {
                context.write(new IntPair(parser.getYearInt(), parser.getAirTemperature()), NullWritable.get());
            }
        }
    }

    public static class MaxTemperatureReducer extends Reducer<IntPair, NullWritable, IntPair, NullWritable> {
        @Override
        protected void reduce(IntPair key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }

    /**
     * 按年份分区
     */
    static class FirstPartitioner extends Partitioner<IntPair, NullWritable> {
        @Override
        public int getPartition(IntPair intPair, NullWritable nullWritable, int numPartitions) {
            return Math.abs(intPair.getFirst() * 127) / numPartitions;
        }
    }

    static class KeyComparator extends WritableComparator {
        public KeyComparator() {
            super(IntPair.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            IntPair p1 = (IntPair) a;
            IntPair p2 = (IntPair) b;
            int cmp = IntPair.compare(p1.getFirst(), p2.getFirst());
            if (cmp != 0) {
                return cmp;
            }
            return - IntPair.compare(p1.getSecond(), p2.getSecond());
        }
    }

    public static class GroupComparator extends WritableComparator {

        public GroupComparator() {
            super(IntPair.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            IntPair p1 = (IntPair) a;
            IntPair p2 = (IntPair) b;
            return IntPair.compare(p1.getFirst(), p2.getFirst());
        }
    }
}
