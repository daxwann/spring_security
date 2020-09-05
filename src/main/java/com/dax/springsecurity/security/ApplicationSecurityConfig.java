package com.dax.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.dax.springsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
//        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        .and()
        .csrf().disable()
        .authorizeRequests()
          .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
          .antMatchers("/api/**").hasRole(STUDENT.name())
          .anyRequest()
          .authenticated()
        .and()
        .formLogin()
          .loginPage("/login").permitAll()
          .defaultSuccessUrl("/courses", true)
          .passwordParameter("password")
          .usernameParameter("username")
          .and()
          .rememberMe() // defaults to 2 weeks
          .rememberMeParameter("remember-me")
          .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
          .key("somethingverysecured")
        .and()
        .logout()
          .logoutUrl("/logout")
          .clearAuthentication(true)
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID", "remember-me")
          .logoutSuccessUrl("/login");
  }

  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails annaUser = User.builder()
        .username("annasmith")
        .password(passwordEncoder.encode("password"))
//        .roles(STUDENT.name()) // ROLE_STUDENT
        .authorities(STUDENT.getGrantedAuthorities())
        .build();

    UserDetails lindaUser = User.builder()
        .username("linda")
        .password(passwordEncoder.encode("password"))
//        .roles(ADMIN.name())
        .authorities(ADMIN.getGrantedAuthorities())
        .build();

    UserDetails tomUser = User.builder()
        .username("tom")
        .password(passwordEncoder.encode("password"))
//        .roles(ADMIN_TRAINEE.name())
        .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
        .build();

    return new InMemoryUserDetailsManager(annaUser, lindaUser, tomUser);
  }
}
