package co.cosmose.kafkademo;

import lombok.Data;

@Data
public class Order {

    private Long orderId;

    private User user;

    private Long amount;
}
