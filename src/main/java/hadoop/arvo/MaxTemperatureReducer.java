package hadoop.arvo;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxTemperatureReducer extends Reducer<AvroKey<Integer>, AvroValue<GenericRecord>,
        AvroKey<GenericRecord>, NullWritable> {
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
    @Override
    protected void reduce(AvroKey<Integer> key, Iterable<AvroValue<GenericRecord>> values, Context context) throws IOException, InterruptedException {
        GenericRecord max = null;
        for (AvroValue<GenericRecord> x : values) {
            GenericRecord record = x.datum();
            if (max == null || (Integer) record.get("temperature") > (Integer) max.get("temperature")) {
                max = newweatherRecord(record);
            }
        }
        context.write(new AvroKey<>(max), NullWritable.get());
    }

    private GenericRecord newweatherRecord(GenericRecord value) {
        GenericRecord record = new GenericData.Record(schema);
        record.put("year", value.get("year"));
        record.put("temperature", value.get("temperature"));
        record.put("stationId", value.get("stationId"));
        return record;
    }
}
