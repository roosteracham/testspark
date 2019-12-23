package hadoop.demo;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.Iterator;

public class WordCountRunner extends Configured implements Tool {

    static class WordCounterReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            Iterator<IntWritable> iterator = values.iterator();
            int sum = 0;
            while (iterator.hasNext()) {
                sum += iterator.next().get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance();
        job.setJarByClass(WordCountRunner.class);
        job.setMapperClass(WordCounterMapper.class);
        job.setReducerClass(WordCounterReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new WordCountRunner(), args);
        System.exit(run);
    }
}
