package hadoop.codec;

import hadoop.排序.MaxRemperatureUsingSecondSort;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.xml.soap.Text;
import java.io.IOException;

public class MaxTemperationCompression {

    public static void main(String[] args) throws IOException {
        Job job = new Job();
        job.setJarByClass(MaxRemperatureUsingSecondSort.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileOutputFormat.setCompressOutput(job, true);
        FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);

        job.setMapperClass(MaxRemperatureUsingSecondSort.MaxTemperatureMapper.class);
        job.setReducerClass(MaxRemperatureUsingSecondSort.MaxTemperatureReducer.class);
    }
}
