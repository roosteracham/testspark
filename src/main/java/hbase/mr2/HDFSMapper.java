package hbase.mr2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HDFSMapper extends Mapper<LongWritable, Text, NullWritable, Put> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\t");

        Put put = new Put(Bytes.toBytes(split[0]));

        put.addColumn("info".getBytes(), "name".getBytes(), split[1].getBytes());
        put.addColumn("info".getBytes(), "color".getBytes(), split[2].getBytes());


        context.write(NullWritable.get(), put);
    }
}
