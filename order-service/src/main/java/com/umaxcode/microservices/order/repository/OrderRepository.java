package com.umaxcode.microservices.order.repository;

import com.umaxcode.microservices.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
