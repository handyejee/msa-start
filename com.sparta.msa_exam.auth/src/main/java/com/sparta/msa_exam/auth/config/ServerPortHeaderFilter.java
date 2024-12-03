package com.sparta.msa_exam.auth.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerPortHeaderFilter implements Filter {

  @Value("{server.port}")
  private String serverPort;


  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.setHeader("Server-Port", serverPort);

    chain.doFilter(request, response);
  }
}
