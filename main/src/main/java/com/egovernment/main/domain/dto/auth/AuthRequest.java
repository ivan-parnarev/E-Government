package com.egovernment.egovbackend.domain.dto.auth;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    String userPin;
}
