package com.egovernment.egovbackend.domain.dto;

public class UserVotedInfoDTO {

    private String voterName;
    private String voteOption;

    public String getVoterName() {
        return voterName;
    }

    public UserVotedInfoDTO setVoterName(String voterName) {
        this.voterName = voterName;
        return this;
    }

    public String getVoteOption() {
        return voteOption;
    }

    public UserVotedInfoDTO setVoteOption(String voteOption) {
        this.voteOption = voteOption;
        return this;
    }

}
