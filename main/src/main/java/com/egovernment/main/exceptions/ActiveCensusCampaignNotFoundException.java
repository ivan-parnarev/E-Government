package com.egovernment.egovbackend.exceptions;

public class ActiveCensusCampaignNotFoundException extends RuntimeException {

    public ActiveCensusCampaignNotFoundException() {
        super("Active Census Campaign Not Found");
    }
}
