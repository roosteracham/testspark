package hadoop.mr;

import hadoop.demo.WordCountRunner;
import hadoop.demo.WordCounterMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ConfTest extends Configured implements Tool {
    public static void main(String[] args) throws Exception {

        int run = ToolRunner.run(new ConfTest(), args);
        System.exit(run);
    }

    private static void readConf() {
        Configuration entries = new Configuration();
        entries.addResource("config/configuration.xml");
        System.out.println(entries.get("color"));
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");
        Job job = new Job(conf, "abc");

        FileInputFormat.addInputPath(job, new Path("text/wc.txt"));
        FileOutputFormat.setOutputPath(job, new Path("text/out.txt"));
        job.setMapperClass(WordCounterMapper.class);
        job.setReducerClass(WordCountRunner.WordCounterReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }
}
