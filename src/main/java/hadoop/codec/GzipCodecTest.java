package hadoop.codec;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class GzipCodecTest {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
//        codecStdIn(args[0]);

        // 拿到文件系统
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), conf);

        // 获取压缩实例
        Path inputPath = new Path(uri);
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(inputPath);

        if (codec == null) {
            System.err.println("no codec found");
            System.exit(-1);
        }

        // 输出目录，这里是将文件名去除扩展名作为输出目录
        String outputUri = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());

        InputStream in = null;
        OutputStream out = null;
        try {
            // 创建输入流
            in = codec.createInputStream(fileSystem.open(inputPath));
            out = fileSystem.create(new Path(outputUri));
            IOUtils.copyBytes(in, out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }

    private static void codecStdIn(String arg) throws ClassNotFoundException, IOException {
        // echo "text" | hadoop jar scala-spark-1.0-SNAPSHOT.jar hadoop.codec.GzipCodecTest org.apache.hadoop.io.compress.GzipCodec | gunzip
        String codecName = arg;
        Class<?> codeClass = Class.forName(codecName);
        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClass, conf);

        CompressionOutputStream outputStream = codec.createOutputStream(System.out);
        IOUtils.copyBytes(System.in, outputStream, 4096, false);
        outputStream.finish();
    }

    public static void useCodecPool(String... args) throws ClassNotFoundException, IOException {
        String codecName = args[0];
        Class<?> codeClass = Class.forName(codecName);
        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClass, conf);

        Compressor compressor = null;
        try {
            compressor = CodecPool.getCompressor(codec);
            CompressionOutputStream outputStream = codec.createOutputStream(System.out, compressor);
            IOUtils.copyBytes(System.in, outputStream, 4096, false);
        } finally {
            CodecPool.returnCompressor(compressor);
        }
    }
}
