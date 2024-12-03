package com.sparta.msa_exam.auth.dto;

import com.sparta.msa_exam.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserResponseDto {

  private String username;

  public static UserResponseDto fromEntity(User user) {
    return UserResponseDto.builder()
        .username(user.getUsername())
        .build();
  }
}
