package org.zsell.listingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsell.listingservice.domain.OrderStatus;
import org.zsell.listingservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void saveAnOrder(OrderStatus orderStatus){
        orderRepository.save(orderStatus);
    }

}