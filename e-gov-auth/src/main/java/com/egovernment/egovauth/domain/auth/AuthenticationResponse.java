package com.egovernment.egovauth.domain.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    public String publicKey;
}
