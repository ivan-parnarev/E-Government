package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.service.CampaignService;
import com.egovernment.egovbackend.web.interfaces.CampaignControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;

    @Operation(summary = "Method Name: getAllActiveCampaigns; Retrieves a list of all active campaigns.")
    @ApiResponses(
            value =  @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved list of active campaigns",
                    content = {@Content(mediaType = "application/json",
                              schema = @Schema(
                                      type = "array",
                                      implementation = CampaignViewDTO.class))})
    )
    @Override
    @GetMapping("/active")
    public ResponseEntity<List<CampaignViewDTO>> getAllActiveCampaigns(){
        List<CampaignViewDTO> campaigns = this.campaignService.getActiveCampaigns();
        return ResponseEntity.ok(campaigns);
    }
}
