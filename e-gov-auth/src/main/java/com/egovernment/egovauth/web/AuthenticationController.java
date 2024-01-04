package com.egovernment.egovauth.web;

import com.egovernment.egovauth.domain.auth.AuthenticationRequest;
import com.egovernment.egovauth.domain.auth.AuthenticationResponse;
import com.egovernment.egovauth.exceptions.UserNotFoundException;
import com.egovernment.egovauth.service.AuthenticationService;
import com.egovernment.egovauth.service.KeyService;
import com.egovernment.egovauth.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value = ApiPaths.BASE_API_PATH)
@RequiredArgsConstructor
public class AuthenticationController {

    @Value("${keyPaths.publicKeyPath}")
    private String publicKeyPath;

    private final AuthenticationService authenticationService;

    private final KeyService keyService;

    @Operation(summary = "Method Name: authenticateUser; Authenticates User if it exist in the Database.")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200",
                    description = "User is authenticated, Jwt token is generated and returned in Header, " +
                            "Public Key is returned.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    implementation = AuthenticationResponse.class))})
                    ,@ApiResponse(responseCode = "401",
                    description = "Unauthorized - User not found.",
                    content = {@Content(mediaType = "application/json")}
            )
            }
    )
    @PostMapping(ApiPaths.AUTH_PATH)
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
            String token = this.authenticationService.authenticateUser(authRequest.getUserPin());

            AuthenticationResponse response = AuthenticationResponse.builder()
                    .publicKey(this.keyService.getPublicKey(publicKeyPath))
                    .build();

            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ex.getMessage());
    }

}
