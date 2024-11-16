package com.umaxcode.microservices.order.service;

import com.umaxcode.microservices.order.client.InventoryClient;
import com.umaxcode.microservices.order.domain.dto.OrderRequestDTO;
import com.umaxcode.microservices.order.domain.dto.OrderResponseDTO;
import com.umaxcode.microservices.order.domain.entity.Order;
import com.umaxcode.microservices.order.exception.OrderException;
import com.umaxcode.microservices.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderResponseDTO placeOrder(OrderRequestDTO request) {

        boolean isProductInStock = inventoryClient.isInStock(request.skuCode(), request.quantity());
        if (!isProductInStock) {
            throw new OrderException("Product with skuCode " + request.skuCode() + " is not in stock");
        }

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(request.price())
                .skuCode(request.skuCode())
                .quantity(request.quantity())
                .build();

        Order savedOrder = orderRepository.save(order);

        return OrderResponseDTO.builder()
                .id(savedOrder.getId())
                .orderNumber(savedOrder.getOrderNumber())
                .skuCode(savedOrder.getSkuCode())
                .quantity(savedOrder.getQuantity())
                .price(savedOrder.getPrice())
                .build();
    }

    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order -> OrderResponseDTO.builder()
                        .id(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .skuCode(order.getSkuCode())
                        .quantity(order.getQuantity())
                        .price(order.getPrice())
                        .build()).toList();
    }
}
