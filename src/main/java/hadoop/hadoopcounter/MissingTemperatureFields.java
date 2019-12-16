package hadoop.hadoopcounter;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MissingTemperatureFields extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        if (strings.length != 1) {
            return -1;
        }
        String id = strings[0];
        Cluster cluster = new Cluster(getConf());
        Job job = cluster.getJob(JobID.forName(id));
        if (null == job) {
            System.err.println("no job found for id: " + id);
            return -1;
        }

        if (!job.isComplete()) {
            System.err.println("job not complete for id: " + id);
            return -1;
        }

        Counters counters = job.getCounters();
        long missing = counters.findCounter(MaxTemWithCounter.Temperature.MISSING).getValue();
        long total = counters.findCounter(TaskCounter.MAP_INPUT_RECORDS).getValue();

        System.out.println("missing fields: %.2f%%\n", 100.0 * missing / total);
        return 0;
    }

    public static void main(String[] args) throws Exception {

        int run = ToolRunner.run(new MaxTemWithCounter(), args);
        System.exit(run);
    }
}
