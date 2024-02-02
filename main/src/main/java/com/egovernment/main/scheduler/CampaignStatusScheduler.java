package com.egovernment.main.scheduler;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.component.CampaignsCatalog;
import com.egovernment.main.domain.enums.CampaignStatus;
import com.egovernment.main.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CampaignStatusScheduler {

    private final CampaignsCatalog campaignsCatalog;
    private final CampaignRepository campaignRepository;

    @Scheduled(cron = "@midnight")
    public void campaignStatusScheduler() {
        List<Campaign> campaigns = this.campaignsCatalog.getCampaigns();
        LocalDateTime today = LocalDateTime.now();

        for (Campaign campaign : campaigns) {
            LocalDateTime startDate = campaign.getStartDate();
            LocalDateTime endDate = campaign.getEndDate();

            if (startDate.equals(today)) {
                campaign.setCampaignStatus(CampaignStatus.ONGOING);
                campaign.setActive(true);
                this.campaignRepository.save(campaign);
            }

            if (endDate.isBefore(today)) {
                campaign.setCampaignStatus(CampaignStatus.PAST);
                campaign.setActive(false);
                this.campaignRepository.save(campaign);
            }
        }
    }
}
