package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductResponseDto {

  private Long productId;
  private String name;
  private Integer supplyPrice;

  public static ProductResponseDto fromEntity(Product product){
    return ProductResponseDto.builder()
        .productId(product.getProductId())
        .name(product.getName())
        .supplyPrice(product.getSupplyPrice())
        .build();
  }
}
