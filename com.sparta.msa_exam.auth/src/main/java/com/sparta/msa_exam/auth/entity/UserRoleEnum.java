package com.sparta.msa_exam.auth.entity;

import com.sparta.msa_exam.auth.entity.UserRoleEnum.Authority;

public enum UserRoleEnum {
  USER(Authority.USER),
  ADMIN(Authority.ADMIN);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
  }
}
