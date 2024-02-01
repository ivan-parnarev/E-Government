package com.egovernment.main.service;

import com.egovernment.main.domain.entity.Role;
import com.egovernment.main.domain.enums.RoleEnum;
import com.egovernment.main.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void initRoles(){
        if(this.roleRepository.count() == 0){

            List<Role> roles = Arrays.stream(RoleEnum.values())
                    .map(Role::new)
                    .toList();

            this.roleRepository.saveAll(roles);

        }
    }

    public Role getRole(RoleEnum roleEnum) {
        return this.roleRepository.findByRoleName(roleEnum);
    }
}
