package avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class FirstDemo {
    public static void main(String[] args) throws Exception {
//        new FirstDemo().parseAvro();
        dataFile();
    }

    /**
     * 从文件读取
     */
    public static void reafdFromFile() throws IOException {
        DatumReader<GenericRecord> reader = new GenericDatumReader<>();
        File file = new File("data.avro");
        DataFileReader<GenericRecord> fileReader = new DataFileReader<>(file, reader);
        System.out.println(fileReader.getSchema().toString());
    }

    /**
     * 数据文件
     */
    public static void dataFile() throws Exception{
        Schema parse = getSchema();


        // 新建一个实例
        GenericData.Record record = getInstance(parse);

        // 将实力序列化到流中
        ByteArrayOutputStream byteArrayOutputStream = writeData(parse, record);

        File file = new File("data.avro");
        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(parse);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(parse, file);
        dataFileWriter.append(record);
        dataFileWriter.close();
    }

    public static void parseAvro() throws IOException {
        Schema parse = getSchema();


        // 新建一个实例
        GenericData.Record record = getInstance(parse);

        // 将实力序列化到流中
        ByteArrayOutputStream byteArrayOutputStream = writeData(parse, record);
//        byteArrayOutputStream.close();

        // 从流中读取avro
        readData(parse, byteArrayOutputStream);
    }

    private static void readData(Schema parse, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        GenericDatumReader<GenericRecord> reader = new GenericDatumReader<>(parse);
        BinaryDecoder binaryEncoder = DecoderFactory.get().binaryDecoder(byteArrayOutputStream.toByteArray(), null);
        GenericRecord record1 = reader.read(null, binaryEncoder);
        System.out.println(record1.get("left").toString());
        System.out.println(record1.get("right"));
    }

    private static ByteArrayOutputStream writeData(Schema parse, GenericData.Record record) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(parse);
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        datumWriter.write(record, encoder);
        encoder.flush();
        return byteArrayOutputStream;
    }

    private static GenericData.Record getInstance(Schema parse) {
        GenericData.Record record = new GenericData.Record(parse);
        record.put("left", "L");
        record.put("right", "R");
        return record;
    }

    private static Schema getSchema() throws IOException {
        // 加载avro模式
        Schema.Parser parser = new Schema.Parser();
        return parser.parse(new File("StringPair.avsc"));
    }
}
