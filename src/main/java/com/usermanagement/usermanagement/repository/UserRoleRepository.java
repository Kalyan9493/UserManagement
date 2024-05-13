package com.usermanagement.usermanagement.repository;

import com.usermanagement.usermanagement.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
