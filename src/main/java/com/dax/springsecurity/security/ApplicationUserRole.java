package com.dax.springsecurity.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.dax.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
  STUDENT(Sets.newHashSet()),
  ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
  ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

  private final Set<ApplicationUserPermission> permissions;

  ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
    this.permissions = permissions;
  }
}
