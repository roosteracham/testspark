package hadoop.filesystem.read;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * hadoop jar scala-spark-1.0-SNAPSHOT.jar hadoop.filesystem.read.FileSystemRead file:///home/h1/xsync
 */
public class FileSystemRead {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), conf);
//        read1(uri, fileSystem);
        FSDataInputStream in = null;
        try {
            in = fileSystem.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0);
            System.out.println("==================");
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }

    private static void read1(String uri, FileSystem fileSystem) throws IOException {
        InputStream in = null;
        try {
            in = fileSystem.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 40994, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
