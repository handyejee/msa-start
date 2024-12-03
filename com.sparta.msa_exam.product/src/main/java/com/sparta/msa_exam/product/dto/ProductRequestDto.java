package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProductRequestDto {

  @NotBlank(message = "상품명을 입력해 주세요.")
  private String name;

  @NotBlank(message = "상품 가격을 입력해주세요.")
  private Integer supplyPrice;

  public Product toEntity(ProductRequestDto requestDto) {
    return Product.builder()
        .name(this.name)
        .supplyPrice(this.supplyPrice)
        .build();
  }
}
