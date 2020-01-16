package hbase.使用hbase作为数据源;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class HbaseAsSource {

    //    static class HbaseAsSourceMapper extends Mapper<ImmutableBytesWritable, Result, Text, Text> {
//        @Override
//        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
//
//        }
//    }
    private static enum Counters {
        ROW,
        SHAKESPEAREAN
    }

    private static boolean containsShakeSpeare(String msg) {
        return true;
    }

    static class HbaseAsSourceMapper extends TableMapper<Text, LongWritable> {
        @Override
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
            byte[] b = value.getColumnLatest("c1".getBytes(), "email".getBytes()).getValue();
            String msg = Bytes.toString(b);
            if (msg!= null && !msg.isEmpty()) {
                context.getCounter(Counters.ROW).increment(1);
            }
            if (containsShakeSpeare(msg)) {
                context.getCounter(Counters.SHAKESPEAREAN).increment(1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("", "");
        Job job = new Job(configuration, "twibase");
        job.setJarByClass(HbaseAsSource.class);

        Scan scan = new Scan();
        scan.addColumn(TwibaseDao.FAMILY.getBytes(), TwibaseDao.COLUMN.getBytes());

        TableMapReduceUtil.initTableMapperJob(TwibaseDao.TABLE_NAME.getBytes(), scan,
                HbaseAsSourceMapper.class,
                ImmutableBytesWritable.class,
                Result.class,
                job);
    }

    static class HbaseAsSourceReducer extends TableReducer<ImmutableBytesWritable, Put, ImmutableBytesWritable> {
        @Override
        protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
            for (Put value : values) {
                context.write(key, value);
            }
        }
    }
}
