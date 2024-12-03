package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.SigninResponseDto;
import com.sparta.msa_exam.auth.dto.UserRequestDto;
import com.sparta.msa_exam.auth.dto.UserResponseDto;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.jwt.JwtUtil;
import com.sparta.msa_exam.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public UserResponseDto signupUser(UserRequestDto request) {

    return UserResponseDto.fromEntity(
        userRepository.save(request.toEntity(passwordEncoder)));
  }

  public SigninResponseDto signin(UserRequestDto request) {
    User user = authenticateUser(request);

    String accessToken = jwtUtil.createAccessToken(user.getUserNumber(), user.getRole());
    String refreshToken = jwtUtil.createRefreshToken(user.getUserNumber(), user.getRole());

    return SigninResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .userNumber(user.getUserNumber())
        .role(user.getRole())
        .build();
  }

  private User authenticateUser(UserRequestDto request) {
    User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("인증에 실패했습니다");
    }
    return user;
  }
}
