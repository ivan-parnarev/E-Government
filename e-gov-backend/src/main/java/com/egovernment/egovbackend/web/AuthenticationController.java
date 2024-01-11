package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.auth.AuthRequest;
import com.egovernment.egovbackend.domain.dto.auth.AuthResponse;
import com.egovernment.egovbackend.exceptions.UserNotFoundException;
import com.egovernment.egovbackend.service.AuthenticationService;
import com.egovernment.egovbackend.web.interfaces.AuthenticationControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerInterface {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticates User",
            description = "Calls Authentication Service via Feign Client with the given details. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User is authenticated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED - Request failed, user is not authorized.")
    })
    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {

        AuthResponse authResponse = authenticationService.authenticateUser(authRequest.getUserPin());

        return ResponseEntity.ok(authResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ex.getMessage());
    }
}
