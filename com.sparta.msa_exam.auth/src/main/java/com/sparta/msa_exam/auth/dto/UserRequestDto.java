package com.sparta.msa_exam.auth.dto;

import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class UserRequestDto {

  @NotBlank(message = "이름을 입력해주세요.")
  private String username;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  private String password;

  private UserRoleEnum role;

  public User toEntity(BCryptPasswordEncoder encoder) {
    return User.builder()
        .username(this.username)
        .password(encoder.encode(password))
        .role(this.role)
        .build();
  }
}
