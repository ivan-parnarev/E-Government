package com.example.egovernmentaccesscontrol.web;

import com.example.egovernmentaccesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.egovernmentaccesscontrol.service.CampaignService;
import com.example.egovernmentaccesscontrol.web.interfaces.CampaignControllerInterface;
import com.example.egovernmentaccesscontrol.web.path.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;

    @Override
    @GetMapping(ApiPaths.ACTIVE_PATH)
    public ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns(){
        List<CampaignFilteredDTO> activeVotingCampaigns = this.campaignService.getActiveVotingCampaigns();
        return ResponseEntity.ok().body(activeVotingCampaigns);
    }

}
