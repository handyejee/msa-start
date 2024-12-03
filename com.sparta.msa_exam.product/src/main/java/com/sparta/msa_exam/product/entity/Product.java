package com.sparta.msa_exam.product.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long productId;

  @Column(nullable = false)
  private String name;

  @Column(name = "supply_price", nullable = false)
  private Integer supplyPrice;

  @Builder
  public Product(String name, Integer supplyPrice) {
    this.name = name;
    this.supplyPrice = supplyPrice;
  }
}
