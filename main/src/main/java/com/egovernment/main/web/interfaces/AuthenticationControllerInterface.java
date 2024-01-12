package com.egovernment.main.web.interfaces;

import com.egovernment.main.domain.dto.auth.AuthRequest;
import com.egovernment.main.domain.dto.auth.AuthResponse;
import com.egovernment.main.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequestMapping(ApiPaths.BASE_API_PATH)
public interface AuthenticationControllerInterface {

    ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
