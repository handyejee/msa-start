package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.UpdateOrderRequest;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
    return ResponseEntity.ok(orderService.createOrder(request));
  }

  @PutMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> updateOrder(
      @PathVariable Long orderId,
      @RequestBody UpdateOrderRequest request) {
    return ResponseEntity.ok(
        orderService.updateOrder(orderId, request)
    );
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
    return ResponseEntity.ok(orderService.getOrder(orderId));
  }
}
