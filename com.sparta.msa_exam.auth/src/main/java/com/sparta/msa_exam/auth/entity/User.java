package com.sparta.msa_exam.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long userId;

  @Column(name = "user_number", columnDefinition = "uuid")
  private UUID userNumber;
  @NotNull
  private String username;
  @NotNull
  private String password;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;

  @PrePersist
  public void generateUserNumber() {
    if (this.userNumber == null) {
      this.userNumber = UUID.randomUUID();
    }
  }

  @Builder
  public User(String username, String password, UserRoleEnum role) {
    this.userNumber = (userNumber != null) ? userNumber : UUID.randomUUID();
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
