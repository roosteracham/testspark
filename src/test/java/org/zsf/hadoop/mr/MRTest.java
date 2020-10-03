package org.zsf.hadoop.mr;

import com.google.common.collect.Lists;
import hadoop.demo.WordCounterMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.WordCount;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

public class MRTest {

    @Test
    public void testMapper() throws IOException {
        Text value = new Text("1 2");
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new WordCounterMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("1"), new IntWritable(1))
                .withOutput(new Text("2"), new IntWritable(1))
                .runTest();
    }

    @Test
    public void testReduce() throws IOException {
        new ReduceDriver<Text, IntWritable, Text, IntWritable>( )
                .withReducer(new WordCount.Reduce())
                .withInput(new Text("1"), Lists.newArrayList(new IntWritable(1), new IntWritable(2)))
                .withOutput(new Text("1"), new IntWritable(3))
                .runTest();
    }
}
