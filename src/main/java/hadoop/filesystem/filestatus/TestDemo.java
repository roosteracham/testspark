package hadoop.filesystem.filestatus;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

/**
 * hadoop jar scala-spark-1.0-SNAPSHOT.jar hadoop.filesystem.filestatus.TestDemo hdfs://had1:9000/
 * 展示目录文件
 */
public class TestDemo {

    public static void main(String[] args) throws IOException {
        String uri = "";
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), configuration);
        Path[] path = new Path[args.length];
        for (int i = 0; i < args.length; i++) {
            path[i] = new Path(args[i]);
        }

        FileStatus[] statuses = fileSystem.listStatus(path);
        Path[] paths = FileUtil.stat2Paths(statuses);
        Arrays.stream(paths).forEach(System.out::println);
    }

}
