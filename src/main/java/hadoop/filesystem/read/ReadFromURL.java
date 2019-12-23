package hadoop.filesystem.read;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * hadoop jar scala-spark-1.0-SNAPSHOT.jar hadoop.filesystem.read.ReadFromURL file:///home/h1/xsync output
 * 可以通过上面的命令查看本地文件系统里面的文件
 */
public class ReadFromURL {
    static {// 每个jvm只能执行一次
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        }
        finally {
            IOUtils.closeStream(in);
        }
    }
}
