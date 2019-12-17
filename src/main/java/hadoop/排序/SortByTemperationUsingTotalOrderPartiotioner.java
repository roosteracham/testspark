package hadoop.排序;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.lib.InputSampler;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;

import java.net.URI;

/**
 * 全排序--将数据集数据全部排序
 * 部分排序--在每个分片那种的数据排序
 * IntervalSampler 以一定时间间隔从数据集采样，教师和已经排好序的数据
 * SplitSampler 值采样一个分片中的钱n条记录，不适合已排好序的数据
 */
public class SortByTemperationUsingTotalOrderPartiotioner
extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        Job job = new Job();
        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        SequenceFileOutputFormat.setCompressOutput(job, true);
        SequenceFileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
        SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);

        job.setPartitionerClass(TotalOrderPartitioner.class);

        // 已制定频率从一个数据集中均匀的采集数据
        InputSampler.Sampler<IntWritable, Text> sampler =
                new InputSampler.RandomSampler<>(0.1, 1000, 10);

        InputSampler.writePartitionFile(job, sampler);

        Configuration configuration = job.getConfiguration();
        String partitionFile = TotalOrderPartitioner.getPartitionFile(configuration);
        URI uri = new URI(partitionFile);
        job.addCacheFile(uri);
        return job.waitForCompletion(true) ? 0: 1;
    }
}
