package hadoop.hadoopcounter;

import hadoop.arvo.MaxTemperatureReducer;
import hadoop.arvo.NcdcRecordParser;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class MaxTemWithCounter extends Configured implements Tool {

    /***
     * Temperature 计时器组
     * MISSING，MALFORMED 计数器名
     *自定义计数器
     **/
    enum Temperature {
        MISSING,
        MALFORMED
    }

    // 使用自定义的计数器
    static class CounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        private NcdcRecordParser parser = new NcdcRecordParser();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            parser.parse(value);
            if (parser.isValidTemperature()) {
                int airTem = parser.getAirTemperature();
                context.write(new Text(parser.getYear()), new IntWritable(airTem));
            } else if (parser.isMalformedTemperature()){
                System.err.println("ignoring possibly corrupt input: " + value);
                context.getCounter(Temperature.MALFORMED).increment(1);
            } else if (parser.isMissingTemperature()) {
                context.getCounter(Temperature.MISSING).increment(1);
            }

            // 动态计数器
            context.getCounter("TemperatureQuality", parser.getQuality()).increment(1);
        }
    }

    @Override
    public int run(String[] strings) throws Exception {

        Job job = new Job();
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(CounterMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new MaxTemWithCounter(), args);
        System.exit(run);
    }
}
