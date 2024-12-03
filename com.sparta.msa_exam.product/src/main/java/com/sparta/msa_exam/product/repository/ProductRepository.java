package com.sparta.msa_exam.product.repository;

import com.sparta.msa_exam.product.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByProductId(Long productId);
  Boolean existsByName(String name);
}
