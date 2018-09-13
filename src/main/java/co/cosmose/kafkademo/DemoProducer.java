package co.cosmose.kafkademo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Properties;


@Component
public class DemoProducer implements ApplicationRunner  {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        produceOldSchool();

        sendViaTemplate();
    }

    private void sendViaTemplate() {
        kafkaTemplate.send("test", "object with key1", "key1").addCallback(
                System.out::println,
                Throwable::printStackTrace);
    }

    private void produceOldSchool() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>("test", "object with key1", "key1");

        kafkaProducer.send(record);

        kafkaProducer.flush();

        kafkaProducer.close();
    }
}
