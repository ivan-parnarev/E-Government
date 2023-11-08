package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.service.CampaignService;
import com.egovernment.egovbackend.web.interfaces.CampaignControllerInterface;
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
    @GetMapping("/active")
    public ResponseEntity<List<CampaignViewDTO>> getAllActiveCampaigns(){
        List<CampaignViewDTO> campaigns = this.campaignService.getActiveCampaigns();
        return ResponseEntity.ok(campaigns);
    }
}
