package kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test implements ConsumerRebalanceListener {

    private Map<TopicPartition, OffsetAndMetadata> currentOffSet = new HashMap<>();

    public static void main(String[] args) {
        consumer();
    }

    public static KafkaConsumer<String, String> getConsumer() {
        Properties properties = new Properties();
        // 这里配置的zookeeper，一台十小时，还可以连接另一台
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "cg1");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        return consumer;
    }

    public static void consumer() {
        KafkaConsumer<String, String> consumer = getConsumer();
        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                consumer.wakeup();
                try {
                    mainThread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        consumer.subscribe(Collections.singleton("love"));
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(String.format("topic: %s, partition = %s, offset= %s, key = %s, value = %s",
                            consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
                            consumerRecord.key(), consumerRecord.value()));
                }
                consumer.commitAsync();
            }
        } finally {
            consumer.commitSync();
            consumer.close();
        }
    }

    /**
     * 通过listener提交
     */
    public void consumerWithListener() {
        KafkaConsumer<String, String> consumer = getConsumer();
        consumer.subscribe(Collections.singleton("jituiniunan"), this);
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
                Map<String, Integer> map = new HashMap<>();
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(String.format("topic: %s, partition = %s, offset= %s, key = %s, value = %s",
                            consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
                            consumerRecord.key(), consumerRecord.value()));
                    int value = 1;
                    if (map.containsKey(consumerRecord.value())) {
                        value = map.get(consumerRecord.value()) + 1;
                    }
                    map.put(consumerRecord.value(), value);

                    currentOffSet.put(new TopicPartition(consumerRecord.topic(), consumerRecord.partition()),
                            new OffsetAndMetadata(consumerRecord.offset() + 1, "no metadata"));
                }
                consumer.commitSync(currentOffSet, null);
            }
        } finally {
            consumer.close();
        }
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        // 在在均衡之前和消费者停止雄安飞之前调用
        // 可以在这里提交offset，关闭各种资源
        System.out.println("offset: " + currentOffSet);
        getConsumer().commitSync();
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {
        // 在重新分配分区和消费者开始读取消息之前被调用

    }

}
