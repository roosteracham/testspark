package hbase.mr2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HDFSDriver extends Configuration implements Tool {

    private Configuration configuration;

    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(configuration);
        job.setJarByClass(HDFSDriver.class);
//        Scan scan = new Scan();
//        TableMapReduceUtil.initTableMapperJob("fruit", scan, MyMapper.class, ImmutableBytesWritable.class, Put.class, job);
        job.setMapperClass(HDFSMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Put.class);
        TableMapReduceUtil.initTableReducerJob("fruit_mr", HDFSReducer.class, job);
        FileInputFormat.setInputPaths(job, strings[0]);
        boolean completion = job.waitForCompletion(true);
        return completion ? 0 : 1;
    }

    @Override
    public void setConf(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration getConf() {
        return configuration;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        //configuration.set("hbase.zookeeper.quorum", "had1");
        ToolRunner.run(configuration, new HDFSDriver(), args);
    }
}


