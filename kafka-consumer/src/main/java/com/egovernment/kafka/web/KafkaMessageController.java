package com.egovernment.kafka.web;

import com.egovernment.kafka.consumer.MessageCatalog;
import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import com.egovernment.kafka.service.KafkaListenerManagementService;
import com.egovernment.kafka.web.interfaces.KafkaMessageControllerInterface;
import com.egovernment.kafka.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KafkaMessageController implements KafkaMessageControllerInterface {

    private final KafkaListenerManagementService kafkaListenerManagementService;

    @Operation(summary = "Get Campaign Results",
            description = "Retrieves the results of the campaigns from the Kafka message catalog.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of campaign results",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserVotedInfoDTO.class))}),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @Override
    @GetMapping(ApiPaths.CONSUME_PATH)
    public ResponseEntity<List<UserVotedInfoDTO>> getCampaignResults(){
        List<UserVotedInfoDTO> campaignsResult = MessageCatalog.getCampaignsResult();
        return ResponseEntity.ok(campaignsResult);
    }

    @Operation(summary = "Delete Messages from Kafka Topic",
            description = "Deletes messages from the specified Kafka topic and stops the associated listener.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Indicates whether the messages were successfully deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @PostMapping("/delete/{topic}")
    public ResponseEntity<Boolean> deleteMessagesTopic(@PathVariable("topic") String topic){
        boolean removeCampaignTopic = MessageCatalog.removeCampaignTopic(topic);
        kafkaListenerManagementService.stopListener(topic);
        return ResponseEntity.ok(removeCampaignTopic);
    }

}
