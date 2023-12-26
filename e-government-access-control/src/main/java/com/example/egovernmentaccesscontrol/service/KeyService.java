package com.example.egovernmentaccesscontrol.service;

import com.example.egovernmentaccesscontrol.domain.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeyService {

    private String jwtToken = "eyJhbGciOiJSUzI1NiJ9.eyJmaXJzdE5hbWUiOiLQk9C10L7RgNCz0LgiLCJtaWRkbGVOYW1lIjoi0JPQtdC-0YDQs9C40LXQsiIsImxhc3ROYW1lIjoi0JPQtdC-0YDQs9C40LXQsiIsImlzQWRtaW4iOmZhbHNlLCJhZGRyZXNzIjp7ImNvdW50cnkiOiLQkdGK0LvQs9Cw0YDQuNGPIiwicmVnaW9uIjoi0KHQvtGE0LjQudGB0LrQsCIsIm11bmljaXBhbGl0eSI6ItCh0YLQvtC70LjRh9C90LAg0L7QsdGJ0LjQvdCwIiwiY2l0eSI6ItCh0L7RhNC40Y8iLCJ2aWxsYWdlIjpudWxsfSwianRpIjoiNGZmNzE5ZWYtMWFmYy00YjcxLTlkMmYtNTE0YzJlODQ2NDNhIiwiaWF0IjoxNzAyOTk4NDI0LCJleHAiOjE3MDQ3MjY0MjR9.RryoCs7KZsCYvtqmvZIwB8gKHIp68_poKAE3Jp_zC6MPn6tu0_QDha061qwI7sdqBEbC2vhXtJqVyD-VhSBnLQHyhq9YitqPKeUbJROEwfVctotuV9-5FpwgbN240ygrXvVDeoxADTdWQVDiDR4IvUPKc964fQ6obSsRNMDHho7RCcf9e-ayGQFMCOD-KXR1AX4rzUYbFSCFVw_oXFv4vOq-MSeu7fmekeE-NVrq1I9y8_jSnnMFUm3pnrfMu0Qmcc49RwaOs0vjGxjov4YcUxWchmAYyYholeq-hTHuxGHRTOap63UCFjAzpcOO_y-Y6d-hv-5fpsYABYsDq560Rw";
    private String publicKeyPEM = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbcHmCTp43khzbtoreiimCHCSxP6irGjBNBoxifXGglQGZjsiL5W7vgHFsfuRDJhDwJrSajBICkN8gmf9dYZY1n7v8zpdtf4YJ2+yUq/CcW6Oqj9T/MczWv9ehuFh2VnIj9L7Vq/1J8QlKg3cgsFPLHcIb1Wi8olCQfTQnjxUeeGhzBHinlvKtgX745R/vb8giMRbF+Ntfxlxgs60eEJtxs2WOTs7BYnxqVcGwxq85ESO+/IC4p8YzIJTpXSrmEAPgRVLKQgeai2GJiG5yha5nHgFRDZZ8V9pquEByx4IyuIa8V43yakq2A+5qin7moOQbtE4wBkc36rzW1JFTaUVQIDAQAB";

    public Address extractAddress() throws NoSuchAlgorithmException, InvalidKeySpecException, JsonProcessingException {
        PublicKey publicKey = this.getPublicKey(publicKeyPEM);
        Jws<Claims> jwtClaims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(jwtToken);

        Claims claims = jwtClaims.getBody();
        Map<String, Object> addressMap = (Map<String, Object>) claims.get("address");

        ObjectMapper mapper = new ObjectMapper();
        String addressJson = mapper.writeValueAsString(addressMap);

        Address address = mapper.readValue(addressJson, Address.class);

        System.out.println("Country: " + address.getCountry());
        System.out.println("Region: " + address.getRegion());
        System.out.println("Municipality: " + address.getMunicipality());
        System.out.println("City: " + address.getCity());
        System.out.println("Village: " + address.getVillage());

        return address;
    }

    private PublicKey getPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

}
