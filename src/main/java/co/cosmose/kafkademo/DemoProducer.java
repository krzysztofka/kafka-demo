package co.cosmose.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Component
public class DemoProducer implements ApplicationRunner  {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        IntStream.range(1, 100)
                .parallel()
                .mapToObj(this::toOrder)
                .forEach(this::sendViaTemplate);
    }

    private Order toOrder(int i) {
        Order order = new Order();
        order.setOrderId(Long.valueOf(i));
        order.setAmount(100L + i);

        User user = new User();
        user.setAge(i);
        user.setId(Long.valueOf(i));
        user.setName("Jogn Doe");

        order.setUser(user);

        return order;
    }

    private void sendViaTemplate(Order order) {
        kafkaTemplate.send("orders", order).addCallback(
                System.out::println,
                Throwable::printStackTrace);
    }
}
