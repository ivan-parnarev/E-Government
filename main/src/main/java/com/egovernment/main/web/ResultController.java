package com.egovernment.main.web;

import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.service.ResultService;
import com.egovernment.main.web.interfaces.ResultControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResultController implements ResultControllerInterface {

    private final ResultService resultService;

    @Override
    @GetMapping
    public ResponseEntity<List<CreateVotingCampaignDTO>> getResults() {
        List<CreateVotingCampaignDTO> campaignsResult = this.resultService.getCampaignsResult();
        return ResponseEntity.ok(campaignsResult);
    }

}
