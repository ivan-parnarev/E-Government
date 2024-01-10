package com.egovernment.egovbackend.init;

import com.egovernment.egovbackend.database.DatabaseInitializer;
import com.egovernment.egovbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final CampaignService campaignService;
    private final ElectionService electionService;
    private final CandidateService candidateService;
    private final CensusQuestionService censusQuestionService;
    private final RegionService regionService;
    private final DatabaseInitializer databaseInitializer;

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initAdministrator();
        this.regionService.initRegions();
        this.campaignService.initSampleCampaign();
        this.electionService
                .initSampleElections(this.campaignService.getCampaignById(1L).get());
        this.candidateService.initSampleCandidates();
        this.censusQuestionService.initTestQuestions();
        this.databaseInitializer.checkAndCreatePublication();
    }
}
