package com.dax.springsecurity.jwt;

import com.google.common.net.HttpHeaders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="application.jwt")
@Configuration
public class JwtConfig {
  private String secretKey;
  private String tokenPrefix;
  private Integer expirationAfterDays;

  public JwtConfig() {
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getTokenPrefix() {
    return tokenPrefix;
  }

  public void setTokenPrefix(String tokenPrefix) {
    this.tokenPrefix = tokenPrefix;
  }

  public Integer getExpirationAfterDays() {
    return expirationAfterDays;
  }

  public void setExpirationAfterDays(Integer expirationAfterDays) {
    this.expirationAfterDays = expirationAfterDays;
  }

  public String getAuthorizationHeader() {
    return HttpHeaders.AUTHORIZATION;
  }
}
