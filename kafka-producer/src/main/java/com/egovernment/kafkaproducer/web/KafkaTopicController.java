package com.egovernment.kafkaproducer.web;

import com.egovernment.kafkaproducer.domain.dto.CampaignsTopicDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.service.KafkaService;
import com.egovernment.kafkaproducer.web.interfaces.KafkaTopicControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaTopicController implements KafkaTopicControllerInterface {

    private final KafkaService kafkaService;

    @Operation(summary = "Create Kafka Topics",
            description = "Creates Kafka topics for each campaign title provided in the request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Topics successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiCustomResponse.class))}),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @Override
    @PostMapping("/create")
    public ResponseEntity<ApiCustomResponse> createCampaignsTopic(CampaignsTopicDTO campaignsTopicDTO) {

        ApiCustomResponse response = kafkaService.createTopics(campaignsTopicDTO);

        return ResponseEntity.ok().body(response);
    }
}
