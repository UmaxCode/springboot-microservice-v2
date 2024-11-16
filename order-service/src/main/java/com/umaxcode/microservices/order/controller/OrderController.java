package com.umaxcode.microservices.order.controller;

import com.umaxcode.microservices.order.domain.dto.OrderRequestDTO;
import com.umaxcode.microservices.order.domain.dto.OrderResponseDTO;
import com.umaxcode.microservices.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {

        return orderService.placeOrder(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDTO> getAllOrders() {

       return orderService.getAllOrders();
    }
}
