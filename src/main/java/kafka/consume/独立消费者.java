package kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;

public class 独立消费者 {


    public void consume() {
        KafkaConsumer<String, String> consumer = Test.getConsumer();
        String topic = "";
        List<PartitionInfo> partitions = consumer.partitionsFor(topic);
        List<TopicPartition> partitionInfoList = new ArrayList<>();
        for (PartitionInfo partition : partitions) {
            partitionInfoList.add(new TopicPartition(partition.topic(), partition.partition()));
        }
        consumer.assign(partitionInfoList);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
            consumer.commitAsync();
        }

    }
}
