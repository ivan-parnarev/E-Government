package com.egovernment.main.exceptions;

public class ActiveCensusCampaignNotFoundException extends RuntimeException {

    public ActiveCensusCampaignNotFoundException() {
        super("Active Census Campaign Not Found");
    }
}
