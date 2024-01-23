package com.egovernment.main.exceptions;

public class CampaignNotFoundException extends RuntimeException {
    //added exception

    public CampaignNotFoundException(String message) {
        super(message);
    }
}
