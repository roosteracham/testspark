package hadoop.arvo;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTemperatureMapper extends Mapper<LongWritable, Text, AvroKey<Integer>, AvroValue<GenericRecord>> {
    public static final String schemaDtr = "{\n" +
            "\"type\" : \"record\",\n" +
            "\"name\" : \"WeatherRecord\",\n" +
            "\"doc\" : \"weather record\",\n" +
            "\"fields\" : [\n" +
            "{\"name\" : \"year\", \"type\" : \"int\"},\n" +
            "{\"name\" : \"temperature\", \"type\" : \"int\"},\n" +
            "{\"name\" : \"stationId\", \"type\" : \"string\"}\n" +
            "]\n" +
            "}";
    private static final Schema schema = new Schema.Parser().parse(schemaDtr);
    private NcdcRecordParser parser = new NcdcRecordParser();
    private GenericRecord record = new GenericData.Record(schema);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());
        if (parser.isValidTemperature()) {
            record.put("year", parser.getYearInt());
            record.put("temperature", parser.getAirTemperature());
            record.put("stationId", parser.getStationId());
            context.write(new AvroKey<>(parser.getYearInt()), new AvroValue<>(record));
        }
    }
}
