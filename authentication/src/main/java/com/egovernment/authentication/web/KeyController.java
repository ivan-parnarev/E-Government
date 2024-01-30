package com.egovernment.authentication.web;

import com.egovernment.authentication.domain.dto.PublicKeyDTO;
import com.egovernment.authentication.service.KeyService;
import com.egovernment.authentication.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.egovernment.authentication.web.path.ApiPaths.PUBLIC_KEY_PATH;

@RestController
@RequestMapping(value = ApiPaths.BASE_API_PATH)
@RequiredArgsConstructor
public class KeyController {

    @Value("${keyPaths.publicKeyPath}")
    private String publicKeyPath;

    private final KeyService keyService;

    @Operation(summary = "Method Name: getPublicKey; Returns the public key.")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200",
                    description = "Returns the Public Key",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    implementation = PublicKeyDTO.class))})
            }
    )
    @GetMapping(PUBLIC_KEY_PATH)
    public ResponseEntity<PublicKeyDTO> getPublicKey() throws IOException {

        String publicKey = this.keyService.getPublicKey(publicKeyPath);
        PublicKeyDTO keyDTO = PublicKeyDTO.builder().publicKey(publicKey)
                .build();

        return ResponseEntity.ok(keyDTO);
    }
}
