package com.example.egovernmentaccesscontrol.web;

import com.example.egovernmentaccesscontrol.domain.dto.VotingCampaignDTO;
import com.example.egovernmentaccesscontrol.service.CampaignService;
import com.example.egovernmentaccesscontrol.web.interfaces.CampaignControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;

    @Override
    @GetMapping
    public ResponseEntity<List<VotingCampaignDTO>> getAllCampaigns(){
        List<VotingCampaignDTO> activeVotingCampaigns = this.campaignService.getActiveVotingCampaigns();
        return ResponseEntity.ok().body(activeVotingCampaigns);
    }

}
