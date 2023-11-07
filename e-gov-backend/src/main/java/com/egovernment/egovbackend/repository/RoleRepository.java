package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleEnum roleEnum);
}
