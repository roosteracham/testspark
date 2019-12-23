package hadoop.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strings = value.toString().split(" ");
        for (int i = 0; i < strings.length; i++) {
            String val = strings[i];
            if (StringUtils.isNotBlank(val)) {
                context.write(new Text(val), new IntWritable(1));
            }
        }
    }
}
