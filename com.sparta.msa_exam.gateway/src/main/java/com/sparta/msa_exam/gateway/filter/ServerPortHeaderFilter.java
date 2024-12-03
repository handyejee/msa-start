package com.sparta.msa_exam.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

@Component
public class ServerPortHeaderFilter implements GlobalFilter {

  private final int serverPort;

  public ServerPortHeaderFilter(@Value("${server.port}") int serverPort) {
    this.serverPort = serverPort;
    System.out.println("Constructor Server Port: " + serverPort);  // 값 확인용
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    System.out.println("Filter Server Port: " + serverPort);  // 값 확인용
    exchange.getResponse().getHeaders().set("Server-Port", String.valueOf(serverPort));  // add 대신 set 사용
    return chain.filter(exchange);
  }
}
