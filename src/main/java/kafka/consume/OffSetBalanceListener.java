package kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.Collections;

public class OffSetBalanceListener implements ConsumerRebalanceListener {
    private KafkaConsumer<String, String> consumer;

    public OffSetBalanceListener(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        commitTransactionDB();
    }

    public void seekConsumer() {
        KafkaConsumer<String, String> consumer = Test.getConsumer();
        consumer.subscribe(Collections.singletonList("jituiniunan"), new OffSetBalanceListener(consumer));
        consumer.poll(0);
        for (TopicPartition partition : consumer.assignment()) {
            consumer.seek(partition, getOffsetFromDB(partition));
        }

        while (true) {
            for (ConsumerRecord<String, String> record : consumer.poll(100)) {
                process(record);
                storeRecordInDB(record);
                saveOffsetInDB(record.topic(), record.partition(), record.offset());
            }
            commitTransactionDB();
        }
    }

    private void commitTransactionDB() {

    }

    private void saveOffsetInDB(String topic, int partition, long offset) {

    }

    private void storeRecordInDB(ConsumerRecord<String, String> record) {

    }

    private void process(ConsumerRecord<String, String> record) {

    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {
        for (TopicPartition partition : collection) {
            consumer.seek(partition, getOffsetFromDB(partition));
        }
    }

    private long getOffsetFromDB(TopicPartition partition) {
        return 0;
    }
}
