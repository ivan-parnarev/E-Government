package com.example.egovernmentaccesscontrol.domain.geography.factory;

import com.example.egovernmentaccesscontrol.domain.geography.Node;

public class NodeFactory {
    public static Node createNode(String name, String postalCode) {
        return Node.builder()
                .name(name)
                .postalCode(postalCode)
                .build();
    }
}
