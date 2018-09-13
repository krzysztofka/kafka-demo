package co.cosmose.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerDemo {

    @KafkaListener(topics = "test")
    public void consume(ConsumerRecord<?, ?> record) {
        System.out.println("Received: " + record);
    }

}
