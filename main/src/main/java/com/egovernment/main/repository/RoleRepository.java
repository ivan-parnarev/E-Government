package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.Role;
import com.egovernment.main.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleEnum roleEnum);
}
