package com.example.egovernmentaccesscontrol.domain.geography;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryStructure {
    private static CountryStructure instance;
    private Map<Node, List<Node>> structure = new HashMap<>();

    public static CountryStructure getInstance() {
        if (instance == null) {
            instance = new CountryStructure();
        }
        return instance;
    }

}
