package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {

  private List<Long> productIds;

  public Order toEntity() {
    Order order = Order.builder()
        .productIds(new ArrayList<>())
        .build();

    // OrderItem 연관관계 설정
    List<OrderItem> orderItems = productIds.stream()
        .map(productId -> OrderItem.builder()
            .order(order)
            .productId(productId)
            .build())
        .toList();

    order.getProductIds().addAll(orderItems);
    return order;
  }
}
