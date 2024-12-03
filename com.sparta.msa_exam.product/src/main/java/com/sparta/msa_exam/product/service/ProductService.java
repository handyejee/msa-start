package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {

  ProductResponseDto addProduct(ProductRequestDto requestDto);
  List<ProductResponseDto> getProductList();
}
