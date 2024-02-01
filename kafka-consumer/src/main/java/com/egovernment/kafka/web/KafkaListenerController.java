package com.egovernment.kafka.web;

import com.egovernment.kafka.domain.dto.ListenerTopicDto;
import com.egovernment.kafka.response.ApiCustomResponse;
import com.egovernment.kafka.response.message.ApiResponseMessage;
import com.egovernment.kafka.service.KafkaListenerCreationService;
import com.egovernment.kafka.service.KafkaListenerManagementService;
import com.egovernment.kafka.web.interfaces.KafkaListenerControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaListenerController implements KafkaListenerControllerInterface {

    private final KafkaListenerCreationService kafkaListenerCreationService;
    private final KafkaListenerManagementService listenerService;

    @Operation(summary = "Create Kafka Listener",
    description = "Creates and registers a Kafka listener for the specified topic.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listener successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiCustomResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @Override
    @PostMapping(path = "/create")
    public ResponseEntity<ApiCustomResponse> createTopicListener(@RequestBody ListenerTopicDto topic) {
        kafkaListenerCreationService.createAndRegisterListener(topic.getTopic());
        ApiCustomResponse apiCustomResponse = ApiCustomResponse
                .builder()
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED_LISTENER)
                .build();
        return ResponseEntity.ok(apiCustomResponse);
    }

    @Operation(summary = "Stop Kafka Listener",
            description = "Stops the Kafka listener with the specified listener ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listener successfully stopped"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @PostMapping("/{listenerId}/stop")
    public void stopListener(@PathVariable String listenerId) {
        listenerService.stopListener(listenerId);
    }

    @Operation(summary = "Start Kafka Listener",
            description = "Starts the Kafka listener with the specified listener ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listener successfully started"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @PostMapping("/{listenerId}/start")
    public void startListener(@PathVariable String listenerId) {
        listenerService.startListener(listenerId);
    }

}
