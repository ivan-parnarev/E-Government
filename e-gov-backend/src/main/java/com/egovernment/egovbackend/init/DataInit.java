package com.egovernment.egovbackend.init;

import com.egovernment.egovbackend.service.CampaignService;
import com.egovernment.egovbackend.service.RoleService;
import com.egovernment.egovbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final CampaignService campaignService;

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initAdministrator();
        this.campaignService.initSampleCampaign();
    }
}
