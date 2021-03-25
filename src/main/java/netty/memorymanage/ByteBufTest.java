package netty.memorymanage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.hadoop.hbase.util.Bytes;

public class ByteBufTest {
    public static void main(String[] args) {
        writeByteBuf();
    }

    /**
     * 堆缓存方式
     */
    static void heapByteBuf() {
        ByteBuf heapBuf = Unpooled.buffer();
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            System.out.println(heapBuf.arrayOffset());
            System.out.println(heapBuf.readerIndex());
            int i = heapBuf.readableBytes();
            System.out.println(i);
        }
    }

    /**
     * 堆缓存方式
     */
    static void directByteBuf() {
        ByteBuf heapBuf = Unpooled.directBuffer();
        if (!heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            System.out.println(heapBuf.arrayOffset());
            System.out.println(heapBuf.readerIndex());
            int i = heapBuf.readableBytes();
            System.out.println(i);
        }
    }

    /**
     * 读数据
     */
    static void readByteBuf() {
        ByteBuf heapBuf = Unpooled.buffer();
        int readableBytes = heapBuf.readableBytes();
        byte[] res = new byte[readableBytes];
        heapBuf.readBytes(res);
        System.out.println(Bytes.toString(res));
    }

    /**
     * 写数据
     */
    static void writeByteBuf() {
        ByteBuf heapBuf = Unpooled.buffer();
        int a = 1;
        System.out.println(heapBuf.readableBytes());
        heapBuf.writeInt(a);
        System.out.println(heapBuf.readableBytes());
        heapBuf.writeShort((short)a);
        System.out.println(heapBuf.readableBytes());
    }
}
