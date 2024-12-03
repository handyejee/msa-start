package com.sparta.msa_exam.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateOrderRequest {

  @JsonProperty("product_id")
  private Long productId;
}
