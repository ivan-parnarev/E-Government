package com.egovernment.egovbackend.domain.dto.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PublicKeyDTO {

    String publicKey;
}
