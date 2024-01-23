package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.domain.entity.Address;
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

    private String jwtToken = "eyJhbGciOiJSUzI1NiJ9.eyJmaXJzdE5hbWUiOiLQk9C10L7RgNCz0LgiLCJtaWRkbGVOYW1lIjoi0JPQtdC-0YDQs9C40LXQsiIsImxhc3ROYW1lIjoi0JPQtdC-0YDQs9C40LXQsiIsImlzQWRtaW4iOmZhbHNlLCJhZGRyZXNzIjp7ImNvdW50cnkiOiLQkdGK0LvQs9Cw0YDQuNGPIiwicmVnaW9uIjoi0KHQvtGE0LjQudGB0LrQsCIsInBvc3Rjb2RlIjoiMTAwMCIsIm11bmljaXBhbGl0eSI6ItCh0YLQvtC70LjRh9C90LAg0L7QsdGJ0LjQvdCwIiwiY2l0eSI6ItCh0L7RhNC40Y8iLCJ2aWxsYWdlIjpudWxsfSwianRpIjoiNmQ5YTFkNmQtYjA2MS00NDhiLWEwNjgtODhiNmMxN2FmZDAzIiwiaWF0IjoxNzA0OTA0MDE4LCJleHAiOjE3MDQ5MDQzMTh9.VUNPn8DmR2shR0nMsecsYmLCsVMlB7s515-zXinrclwAVX2YST67nL6GJ46wWwFQ6H775MMd57j2FNZM8JkKksCORCo6wF2rKbGeZJlzCvXjSCU4z488_Rf-zn7LQv2Ol59Q3d3N-VPfszFMJyCAvx6zszktt5lG2tqvkVcWGoxHd1UkBQp8n5wHquuh9OLWbdvt5BPMfI4A4eZZRg4hxMBXLeutcWuj3OdlgTWjxNgs7kprHWkArVW0XskSwKQoEL5ezceXQVF9GEJdUmQ0jwz21hsilT7WGRhtYYw4Ds7jKhRyquYxTatcAsde244UbCgrRWfWFPI3smNrz5XatQ";
    private String publicKeyPEM = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu5vN/vMzDhLVGap1j8snk9xyJUvmBBD96cJQCG3AvU5WJmnYuabNfMkrGV97TUNfl1BpK/WbotkVsaAgX5t28eb54O+QmiDG+h6eacNrwnMxyAeJZSCTW9IeRrLpihXCm5TzG9JPibcCVgcdf3vPj5j4PCw5klwkpD6GsKvPFlN9oVX+d7R6Vvo7r94DzrbQptKF94yp8BuSOmJOdFZZQh7lgwtuSfUiKaETvJfv8O1MAjmvXz1Um2lsGirTaLvqK9dfZUssrqbW2UAuLx6J5c4x4fU+V5SVnh4OIMgF07NWyjmgc51jF8FynkoAbfLq9wtNX8DdOf1F9qZTgFTywQIDAQAB";

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

        System.out.println("Region: " + address.getRegion());

        return address;
    }

    private PublicKey getPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

}
