package hadoop.filesystem.deletedata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class DeleteDromHdfs {

    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), conf);
        boolean delete = fileSystem.delete(new Path(args[1]), false);
    }
}
