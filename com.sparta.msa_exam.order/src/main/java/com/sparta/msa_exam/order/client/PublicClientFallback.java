package com.sparta.msa_exam.order.client;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class PublicClientFallback implements ProductClient{

  @Override
  public ProductResponseDto getProduct(Long productId) {
    throw new HttpServerErrorException(
        HttpStatus.SERVICE_UNAVAILABLE,
        "잠시 후에 주문 추가를 요청 해주세요."
    );
  }
}
