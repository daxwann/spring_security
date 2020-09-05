package com.dax.springsecurity.auth;

import java.util.Optional;

public interface ApplicationUserDAO {
  Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
