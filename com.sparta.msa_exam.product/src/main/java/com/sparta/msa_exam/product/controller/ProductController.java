package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @Value("${server.port}")
  private String serverPort;

  @PostMapping
  public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto requestDto) {

    ProductResponseDto responseDto = productService.addProduct(requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getProductList() {
    List<ProductResponseDto> products = productService.getProductList();
    return ResponseEntity.ok(products);
  }
}
