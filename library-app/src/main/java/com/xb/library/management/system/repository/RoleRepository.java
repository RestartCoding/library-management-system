package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
