package hadoop.arvo;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MaxTemperatureDriver extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        Job job = new Job(getConf(), "MT");
        job.setJarByClass(getClass());

        job.getConfiguration().setBoolean(Job.MAPREDUCE_JOB_USER_CLASSPATH_FIRST, true);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new MaxTemperatureDriver(), args);
        System.exit(run);
    }
}
