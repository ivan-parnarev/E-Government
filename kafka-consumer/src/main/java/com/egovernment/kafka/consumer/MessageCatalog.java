package com.egovernment.kafka.consumer;

import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Builder
public class MessageCatalog {

    private static Map<String, List<UserVotedInfoDTO>> voteMessagesMap = new HashMap<>();

    public static void addVoteToTopic(String topicName, UserVotedInfoDTO vote){
        voteMessagesMap.putIfAbsent(topicName, new ArrayList<>());
        List<UserVotedInfoDTO> currentVotes = voteMessagesMap.get(topicName);
        currentVotes.add(vote);
        voteMessagesMap.put(topicName, currentVotes);
    }

    public static void removeTopic(String topicName){
        voteMessagesMap.remove(topicName);
    }

    public static void printEntrySet(){
        for (Map.Entry<String, List<UserVotedInfoDTO>> e : voteMessagesMap.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    public static List<UserVotedInfoDTO> getCampaignsResult(){

        List<UserVotedInfoDTO> campaignResultDTOS = new ArrayList<>();

        for (Map.Entry<String, List<UserVotedInfoDTO>> campaign : voteMessagesMap.entrySet()) {
            campaignResultDTOS.addAll(campaign.getValue());

        }
        return campaignResultDTOS;
    }

}
