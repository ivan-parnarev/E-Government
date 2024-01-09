package com.example.egovernmentaccesscontrol.service;

import com.example.egovernmentaccesscontrol.domain.Campaign;
import com.example.egovernmentaccesscontrol.domain.dto.VotingCampaignDTO;
import com.example.egovernmentaccesscontrol.enums.CampaignType;
import com.example.egovernmentaccesscontrol.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<VotingCampaignDTO> getActiveVotingCampaigns(){
        return this.campaignRepository
                .findAll()
                .stream()
                .filter(c -> c.getCampaignType().equals(CampaignType.VOTING))
                .filter(Campaign::isActive)
                .map(c -> this.modelMapper.map(c, VotingCampaignDTO.class))
                .collect(Collectors.toList());
    }

}
