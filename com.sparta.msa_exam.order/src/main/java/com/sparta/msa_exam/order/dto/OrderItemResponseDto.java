package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemResponseDto {

  private Long id;
  private Long productId;

  public static OrderItemResponseDto fromEntity(OrderItem orderItem) {
    return OrderItemResponseDto.builder()
        .id(orderItem.getId())
        .productId(orderItem.getProductId())
        .build();
  }
}
