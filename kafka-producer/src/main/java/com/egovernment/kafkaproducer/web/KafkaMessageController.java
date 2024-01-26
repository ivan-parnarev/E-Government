package com.egovernment.kafkaproducer.web;

import com.egovernment.kafkaproducer.domain.entity.UserVotedInfoDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.service.KafkaService;
import com.egovernment.kafkaproducer.web.interfaces.KafkaMessageControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaMessageController implements KafkaMessageControllerInterface {

    private final KafkaService kafkaSendMessageService;

    @Operation(summary = "Send Kafka Message",
            description = "Sends a message to a Kafka topic based on the campaign title provided in the message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Message successfully sent to Kafka topic",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiCustomResponse.class))}),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @Override
    @PostMapping("/send")
    public ResponseEntity<ApiCustomResponse> sendMessage
            (@RequestBody UserVotedInfoDTO userVotedInfoDTO){

        ApiCustomResponse response = kafkaSendMessageService
                .sendMessage(userVotedInfoDTO, userVotedInfoDTO.getCampaignTitle());

        return ResponseEntity
                .ok()
                .body(response);

    }

}
