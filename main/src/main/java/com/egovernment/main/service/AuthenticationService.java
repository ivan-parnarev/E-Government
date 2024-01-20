package com.egovernment.main.service;

import com.egovernment.main.client.AuthenticationClient;
import com.egovernment.main.domain.dto.auth.AuthRequest;
import com.egovernment.main.domain.dto.auth.AuthResponse;
import com.egovernment.main.domain.dto.auth.FeignAuthResponse;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.region.AddressDTO;
import com.egovernment.main.exceptions.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationClient authenticationClient;
    private final CampaignService campaignService;
    private final ModelMapper modelMapper;

    public ResponseEntity<AuthResponse>  authenticateUser(String hashedUserPin) throws NoSuchAlgorithmException, InvalidKeySpecException {

        AuthRequest authRequest = AuthRequest.builder()
                .userPin(hashedUserPin)
                .build();

        ResponseEntity<FeignAuthResponse> responseFromClient = this.authenticationClient.authenticateUser(authRequest);
        HttpStatusCode statusCode = responseFromClient.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            Boolean isAdmin = checkIsUserAdmin(Objects.requireNonNull(responseFromClient));

            if (isAdmin) {
                String token = extractToken(responseFromClient);

                AuthResponse response = AuthResponse.builder()
                        .isAdmin(true)
                        .build();

                return ResponseEntity.status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + token).body(response);
            } else {

                String token = extractToken(responseFromClient);
                Object address = extractClaimsFromToken(responseFromClient.getBody(), token)
                        .getBody().get("address");

                AddressDTO addressDTO = this.modelMapper.map(address, AddressDTO.class);
                String region = addressDTO.getRegion();

                List<CampaignFilteredDTO> filteredCampaigns = this.campaignService.getActiveCampaigns(region);
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

    private PublicKey createPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

    private Boolean checkIsUserAdmin(ResponseEntity<FeignAuthResponse> responseFromClient) throws NoSuchAlgorithmException, InvalidKeySpecException {
        FeignAuthResponse body = responseFromClient.getBody();
        String token = extractToken(responseFromClient);

        Jws<Claims> jwtClaims = extractClaimsFromToken(body, token);

        return (boolean) jwtClaims.getBody().get("isAdmin");
    }

    private static String extractToken(ResponseEntity<FeignAuthResponse> responseFromClient) {
        String token = Objects.requireNonNull(responseFromClient.getHeaders().get("Authorization")).get(0);
        token = token.replace("Bearer ", "");
        return token;
    }

    private Jws<Claims> extractClaimsFromToken(FeignAuthResponse body, String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey publicKey = createPublicKey(Objects.requireNonNull(body).getPublicKey());

        Jws<Claims> jwtClaims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
        return jwtClaims;
    }
}
