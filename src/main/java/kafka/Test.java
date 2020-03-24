package kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public class Test<K, V> {
    public static void main(String[] args) {

    }

    public static Producer<String, String> getKafkaProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "had1:9092,had3:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("valoe.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        return producer;
    }

    /**
     * 发送消息
     */
    public static void sendMsg() {
        Producer<String, String> producer = getKafkaProducer();
        ProducerRecord<String, String> record = getStringRecord("love", "zsf", "mhl");
        try {
            RecordMetadata metadata = producer.send(record).get();
            System.out.println(JSON.toJSONString(metadata));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMsgAysc() {
        Producer<String, String> producer = getKafkaProducer();
        ProducerRecord<String, String> record = getStringRecord("love", "zsf", "mhl");
        try {
            producer.send(record, new ProduceCallBack());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static ProducerRecord<String, String> getStringRecord(String topic, String key, String val) {
        return new ProducerRecord<>(topic, key, val);
    }

    static class ProduceCallBack implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            System.out.println(JSON.toJSONString(recordMetadata));
            if (e != null) {
                e.printStackTrace();
            }
        }
    }
}
