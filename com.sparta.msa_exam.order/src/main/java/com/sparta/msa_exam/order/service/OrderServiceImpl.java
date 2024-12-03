package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.UpdateOrderRequest;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;
import com.sparta.msa_exam.order.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ProductClient productClient;

  @Transactional
  public OrderResponseDto createOrder(OrderRequestDto request) {
    request.getProductIds().forEach(productId -> {
      try {
        productClient.getProduct(productId); // productClient 에서 조회
      } catch (FeignException e) {
        throw new IllegalArgumentException("Product not found: " + productId);
      }
    });
    Order savedOrder = orderRepository.save(request.toEntity());

    return OrderResponseDto.fromEntity(savedOrder);
  }

  // 주문 단건 조회
  @Transactional(readOnly = true)
  public OrderResponseDto getOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다"));

    return OrderResponseDto.fromEntity(order);
  }

  // 주문에 상품을 추가
  public OrderResponseDto updateOrder(Long orderId, UpdateOrderRequest request) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

    try {
      productClient.getProduct(request.getProductId());
    } catch (FeignException e) {
      throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
    }

    OrderItem orderItem = OrderItem.builder()
        .order(order)
        .productId(request.getProductId())
        .build();

    order.getProductIds().add(orderItem);

    return OrderResponseDto.fromEntity(order);
  }
}
