package com.egovernment.main.client;

import com.egovernment.main.domain.dto.auth.AuthRequest;
import com.egovernment.main.domain.dto.auth.FeignAuthResponse;
import com.egovernment.main.domain.dto.key.PublicKeyDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-client",
        url = "${spring.cloud.openfeign.client.config.authentication-client.url}")
public interface AuthenticationClient {
    @PostMapping("/authenticate")
    ResponseEntity<FeignAuthResponse> authenticateUser(@RequestBody AuthRequest authRequest);

    @Cacheable("publicKey")
    @GetMapping("/publicKey")
    ResponseEntity<PublicKeyDTO> getPublicKey();
}
