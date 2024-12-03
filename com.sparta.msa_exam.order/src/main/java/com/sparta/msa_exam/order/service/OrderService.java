package com.sparta.msa_exam.order.service;


import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.UpdateOrderRequest;

public interface OrderService {

  OrderResponseDto createOrder(OrderRequestDto request);

  OrderResponseDto getOrder(Long orderId);

  OrderResponseDto updateOrder(Long orderId, UpdateOrderRequest request);
}

