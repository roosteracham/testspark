package hbase.mr2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

public class HDFSReducer extends TableReducer<NullWritable, Put, NullWritable> {
    @Override
    protected void reduce(NullWritable key, Iterable<Put> values, Context context)  {
        values.forEach(value -> {
            try {
                context.write(NullWritable.get(), value);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
