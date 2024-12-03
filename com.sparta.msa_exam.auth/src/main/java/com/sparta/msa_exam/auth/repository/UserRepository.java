package com.sparta.msa_exam.auth.repository;

import com.sparta.msa_exam.auth.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
  Optional<User> findByUserNumber(UUID userNumber);
}
