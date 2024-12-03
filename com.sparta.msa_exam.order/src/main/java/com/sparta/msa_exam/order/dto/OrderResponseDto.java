package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

  private Long orderId;
  private List<Long> productIds;

  public static OrderResponseDto fromEntity(Order order) {
    return OrderResponseDto.builder()
        .orderId(order.getOrderId())
        .productIds(order.getProductIds().stream()
            .map(OrderItem::getProductId)
            .collect(Collectors.toList()))
        .build();
  }
}
