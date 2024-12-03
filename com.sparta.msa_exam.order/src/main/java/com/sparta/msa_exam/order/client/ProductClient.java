package com.sparta.msa_exam.order.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", fallback = PublicClientFallback.class) // fallback 로직 실행
public interface ProductClient {

  @GetMapping("/product/{productId}")
  ProductResponseDto getProduct(@PathVariable("productId") Long productId);

  @Getter
  @NoArgsConstructor
  class ProductResponseDto {

    private Long id;
    private String name;
    private Integer price;
  }
}
