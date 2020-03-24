package zookeeper.serial;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author rooster
 */
public class MockReqHeader implements Record {

    private long sessionId;

    private String type;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MockReqHeader() {
    }

    public MockReqHeader(long sessionId, String type) {
        this.sessionId = sessionId;
        this.type = type;
    }

    @Override
    public void serialize(OutputArchive archive, String tag) throws IOException {
        archive.startRecord(this, tag);
        archive.writeLong(sessionId, "sessionId");
        archive.writeString(type, "type");
        archive.endRecord(this, tag);
    }

    @Override
    public void deserialize(InputArchive archive, String tag) throws IOException {
        archive.startRecord(tag);
        this.sessionId = archive.readLong("sessionId");
        this.type = archive.readString("type");
        archive.endRecord(tag);
    }

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryOutputArchive archive = BinaryOutputArchive.getArchive(byteArrayOutputStream);
        new MockReqHeader(123L, "ping").serialize(archive, "header");

        ByteBuffer bb = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        // 反序列化
        ByteBufferInputStream byteBufferInputStream = new ByteBufferInputStream(bb);
        BinaryInputArchive inputArchive = BinaryInputArchive.getArchive(byteBufferInputStream);
        MockReqHeader header = new MockReqHeader();
        header.deserialize(inputArchive, "header");
        System.out.println(header);
        byteBufferInputStream.close();
        byteArrayOutputStream.close();
    }

    @Override
    public String toString() {
        return "MockReqHeader{" +
                "sessionId=" + sessionId +
                ", type='" + type + '\'' +
                '}';
    }
}
