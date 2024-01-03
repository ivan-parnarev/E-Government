package com.example.egovernmentaccesscontrol.web.interfaces;

import com.example.egovernmentaccesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.egovernmentaccesscontrol.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CAMPAIGN_PATH)
public interface CampaignControllerInterface {

    ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns();

}
