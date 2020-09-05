package com.dax.springsecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.dax.springsecurity.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO{
  private final PasswordEncoder passwordEncoder;

  public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    return getApplicationUsers()
            .stream()
            .filter(applicationUser -> username.equals(applicationUser.getUsername()))
            .findFirst();
  }

  private List<ApplicationUser> getApplicationUsers() {
    List<ApplicationUser> applicationUsers = Lists.newArrayList(
        new ApplicationUser(
            STUDENT.getGrantedAuthorities(),
            passwordEncoder.encode("password"),
            "anna",
            true,
            true,
            true,
            true
        ),
        new ApplicationUser(
            ADMIN.getGrantedAuthorities(),
            passwordEncoder.encode("password"),
            "linda",
            true,
            true,
            true,
            true
        ),
        new ApplicationUser(
            ADMIN_TRAINEE.getGrantedAuthorities(),
            passwordEncoder.encode("password"),
            "tom",
            true,
            true,
            true,
            true
        )
    );

    return applicationUsers;
  };
}
