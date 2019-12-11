package avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class FirstDemo {
    public static void main(String[] args) throws IOException {
        new FirstDemo().parseAvro();
    }

    public void parseAvro() throws IOException {

        // 加载avro模式
        Schema.Parser parser = new Schema.Parser();
        Schema parse = parser.parse(new File("StringPair.avsc"));

        // 新建一个实例
        GenericData.Record record = new GenericData.Record(parse);
        record.put("left", "L");
        record.put("right", "R");

        // 将实力序列化到流中
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(parse);
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        datumWriter.write(record, encoder);
        encoder.flush();
//        byteArrayOutputStream.close();

        // 从流中读取avro
        GenericDatumReader<GenericRecord> reader = new GenericDatumReader<>(parse);
        BinaryDecoder binaryEncoder = DecoderFactory.get().binaryDecoder(byteArrayOutputStream.toByteArray(), null);
        GenericRecord record1 = reader.read(null, binaryEncoder);
        System.out.println(record1.get("left").toString());
        System.out.println(record1.get("right"));
    }
}
