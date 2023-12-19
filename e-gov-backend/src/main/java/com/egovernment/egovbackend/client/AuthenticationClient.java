package com.egovernment.egovbackend.client;

import com.egovernment.egovbackend.domain.dto.auth.AuthRequest;
import com.egovernment.egovbackend.domain.dto.auth.FeignAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service-client", url = "http://localhost:8081/api/v1")
public interface AuthenticationClient {
    @PostMapping("/authenticate")
    ResponseEntity<FeignAuthResponse> authenticateUser(@RequestBody AuthRequest authRequest);
}
