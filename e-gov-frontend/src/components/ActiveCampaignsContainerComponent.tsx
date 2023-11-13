import { useEffect, useState } from "react";
import { ActiveCampaign } from "./ActiveCampaignComponent";
import styles from "./ActiveCampaignsContainerComponent.module.css";

interface Campaign {
  id: string;
  campaignTopic: string;
  answersJson: string;
}

export function ActiveCampaignsContainerComponent() {
  const [activeCampaigns, setActiveCampaigns] = useState<Campaign[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/campaigns/active")
      .then((response) => response.json())
      .then((data) => {
        setActiveCampaigns(data);
      })
      .catch((error) =>
        console.error("Error fetching active campaigns:", error)
      );
  }, []);

  return (
    <div className={styles.activeCampaignsSection}>
      <h2 className={styles.subTitle}>Активни кампании:</h2>
      <div className={styles.activeCampaignsButtonsGroup}>
        {activeCampaigns.map((campaign) => {
          return (
            <ActiveCampaign
              key={campaign.id}
              campaignTopic={campaign.campaignTopic}
              campaignId={campaign.id}
              answersJson={JSON.parse(campaign.answersJson)}
            />
          );
        })}
      </div>
    </div>
  );
}
