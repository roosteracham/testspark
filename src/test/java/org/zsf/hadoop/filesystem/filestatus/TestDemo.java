package org.zsf.hadoop.filesystem.filestatus;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class TestDemo {
    private MiniDFSCluster cluster;
    private FileSystem fileSystem;

    @Before
    public void setup() throws Exception{
        Configuration configuration = new Configuration();
        if (System.getProperty("test.build.data") == null) {
            System.setProperty("test.build.data", "/tmp");
        }

        cluster = new MiniDFSCluster.Builder(configuration).build();

        fileSystem = cluster.getFileSystem();
        OutputStream out = fileSystem.create(new Path("/dir/file"));
        out.write("content".getBytes("UTF-8"));
        out.close();
    }

    @After
    public void tearDown() throws IOException {
        if (fileSystem != null) {
            fileSystem.close();
        }

        if (cluster != null) {
            cluster.shutdown();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void throwFileNotFoundException() throws IOException {
        fileSystem.getFileStatus(new Path("no-such-file"));
    }

    @Test
    public void fileStatusForFile() throws Exception {
        Path file = new Path("/dir/file");
        FileStatus fileStatus = fileSystem.getFileStatus(file);
        System.out.println(fileStatus.getPath().toUri().getPath());
    }

    @Test
    public void fileStatusForDir() throws Exception {
        Path file = new Path("/dir");
        FileStatus fileStatus = fileSystem.getFileStatus(file);
        System.out.println(fileStatus.getPath().toUri().getPath());
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testListStatus() throws IOException {
        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/dir/file"));
        FileStatus[] status = fileSystem.listStatus(new Path("/dir/file"));
    }
}
