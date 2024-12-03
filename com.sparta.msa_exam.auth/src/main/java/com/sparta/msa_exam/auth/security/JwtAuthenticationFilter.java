package com.sparta.msa_exam.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.msa_exam.auth.dto.SigninResponseDto;
import com.sparta.msa_exam.auth.dto.UserRequestDto;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.entity.UserRoleEnum;
import com.sparta.msa_exam.auth.jwt.JwtUtil;
import com.sparta.msa_exam.auth.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtUtil jwtUtil;
  private final UserService authService;

  public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService authService) {
    this.jwtUtil = jwtUtil;
    this.authService = authService;
    setFilterProcessesUrl("/api/auth/signin");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      UserRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
          UserRequestDto.class);

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              requestDto.getUsername(),
              requestDto.getPassword(),
              null
          )
      );
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult)
      throws IOException {
    UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
    User user = userDetails.getUser();
    UserRoleEnum role = user.getRole();
    UUID userNumber = user.getUserNumber();

    String accessToken = jwtUtil.createAccessToken(userNumber, role);
    String refreshToken = jwtUtil.createRefreshToken(userNumber, role);

    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

    SigninResponseDto signinResponseDto = SigninResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .userNumber(userNumber)
        .role(role)
        .build();

    response.setContentType("application/json");
    new ObjectMapper().writeValue(response.getWriter(), signinResponseDto);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException {

    Map<String, Object> errorDetails = new HashMap<>();
    errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    errorDetails.put("message", "인증에 실패했습니다");

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    new ObjectMapper().writeValue(response.getWriter(), errorDetails);
  }
}
