package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.SigninResponseDto;
import com.sparta.msa_exam.auth.dto.UserRequestDto;
import com.sparta.msa_exam.auth.dto.UserResponseDto;
import com.sparta.msa_exam.auth.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

  UserResponseDto signupUser(UserRequestDto request);
  SigninResponseDto signin(@RequestBody @Valid UserRequestDto request);
}
