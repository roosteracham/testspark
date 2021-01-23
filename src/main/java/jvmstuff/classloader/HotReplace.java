package jvmstuff.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class HotReplace extends ClassLoader {

    public HotReplace() {
    }

    @Override
    public Class<?> findClass(String name) {
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File("D:\\IdeaProjects\\testspark\\src" +
                        "\\main\\java\\jvmstuff\\classloader\\ClassDemoA.class"));
                FileChannel fileChannel = fileInputStream.getChannel();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                WritableByteChannel channel = Channels.newChannel(outputStream);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while (true) {
                    int i = fileChannel.read(buffer);
                    if (i == 0 || i == -1) {
                        break;
                    }
                    buffer.flip();
                    channel.write(buffer);
                    buffer.clear();
                }
                fileChannel.close();
                byte[] bytes = outputStream.toByteArray();
                loadedClass = defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return loadedClass;
    }
}
