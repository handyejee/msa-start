package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.SigninResponseDto;
import com.sparta.msa_exam.auth.dto.UserRequestDto;
import com.sparta.msa_exam.auth.dto.UserResponseDto;
import com.sparta.msa_exam.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<UserResponseDto> signup(@RequestBody @Valid UserRequestDto request) {
    UserResponseDto response = userService.signupUser(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/signin")
  public ResponseEntity<SigninResponseDto> signin(@RequestBody @Valid UserRequestDto request) {
    SigninResponseDto responseDto = userService.signin(request);
    return ResponseEntity.ok(responseDto);
  }
}
