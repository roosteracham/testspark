package hadoop.filesystem.write;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

public class CopyFromLocal {

    /**
     * hadoop jar scala-spark-1.0-SNAPSHOT.jar hadoop.filesystem.write.CopyFromLocal input/README.txt hdfs://had1/user/tom/1400-8.txt
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String localSrc = args[0];
        String dst = args[1];
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(dst), conf);

        OutputStream outputStream = fileSystem.create(new Path(dst), new Progressable() {
            @Override
            public void progress() {
                System.out.println(".");
            }
        });

        IOUtils.copyBytes(in, outputStream, 4096, true);
    }
}
