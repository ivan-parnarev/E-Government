package com.egovernment.main.service;

import com.egovernment.main.client.AccessControlClient;
import com.egovernment.main.client.AuthenticationClient;
import com.egovernment.main.domain.dto.auth.AuthRequest;
import com.egovernment.main.domain.dto.auth.AuthResponse;
import com.egovernment.main.domain.dto.auth.FeignAuthResponse;
import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.region.AddressDTO;
import com.egovernment.main.exceptions.UserNotFoundException;
import com.egovernment.main.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ModelMapper modelMapper;
    private final AuthenticationClient authenticationClient;
    private final JwtTokenUtil jwtTokenUtil;
    private final AccessControlClient accessControlClient;

    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {

        ResponseEntity<FeignAuthResponse> responseFromClient = this.authenticationClient.authenticateUser(authRequest);
        HttpStatusCode statusCode = responseFromClient.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            String token = this.jwtTokenUtil.extractToken(responseFromClient);
            Boolean isAdmin = this.jwtTokenUtil.validateTokenForAdminRole(token);

            if (isAdmin) {
                AuthResponse response = AuthResponse.builder()
                        .isAdmin(true)
                        .build();

                return ResponseEntity.status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + token)
                        .body(response);
            } else {
                List<CampaignFilteredDTO> filteredCampaigns = getCampaignFilteredDTOS(responseFromClient, token);

                AuthResponse response = AuthResponse.builder()
                        .filteredCampaigns(filteredCampaigns)
                        .build();

                return ResponseEntity.status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + token)
                        .body(response);
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    private List<CampaignFilteredDTO> getCampaignFilteredDTOS(ResponseEntity<FeignAuthResponse> responseFromClient, String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyString = Objects.requireNonNull(responseFromClient.getBody()).getPublicKey();
        Object address = this.jwtTokenUtil.extractClaimsFromToken(publicKeyString, token)
                .getBody().get("address");

        AddressDTO addressDTO = this.modelMapper.map(address, AddressDTO.class);
        String region = addressDTO.getCity();

        return this.accessControlClient.getActiveCampaigns(region).getBody();
    }

}