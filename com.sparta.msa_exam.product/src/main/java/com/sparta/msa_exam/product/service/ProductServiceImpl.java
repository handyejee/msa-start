package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductResponseDto addProduct(ProductRequestDto requestDto) {

    validateProductName(requestDto.getName());

    Product product = requestDto.toEntity(requestDto);
    Product savedProduct = productRepository.save(product);

    return ProductResponseDto.fromEntity(savedProduct);
  }

  public List<ProductResponseDto> getProductList() {
    List<Product> productList = productRepository.findAll();
    return productList.stream()
        .map(ProductResponseDto::fromEntity)
        .collect(Collectors.toList());
  }

  private Product validateProductId(Long productId) {
    return productRepository.findByProductId(productId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
  }

  private void validateProductName(String productName) {
    if (productRepository.existsByName(productName)) {
      throw new IllegalArgumentException("이미 존재하는 상품명 입니다.");
    }
  }
}
