package org.zsell.listingservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.zsell.listingservice.domain.OrderStatus;
import org.zsell.listingservice.service.OrderService;

@Component
@RequiredArgsConstructor
public class QueueListener {

    private final OrderService orderService;

    @RabbitListener(queues = "javatechie_queue")
    public void sendOrderConfirmation(OrderStatus orderStatus) {
        orderService.saveAnOrder(orderStatus);

    }
}
