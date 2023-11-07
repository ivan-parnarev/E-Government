package com.egovernment.egovbackend.init;

import com.egovernment.egovbackend.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final RoleService roleService;

    public DataInit(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
    }
}
