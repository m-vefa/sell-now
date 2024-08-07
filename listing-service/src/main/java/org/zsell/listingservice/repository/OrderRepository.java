package org.zsell.listingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zsell.listingservice.domain.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<OrderStatus, Integer> {

}