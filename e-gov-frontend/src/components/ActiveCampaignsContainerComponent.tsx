import { useEffect, useState } from "react";
import { ActiveCampaignComponent } from "./ActiveCampaignComponent";
import styles from "./ActiveCampaignsContainerComponent.module.css";

interface Campaign {
  electionId: string;
  campaignType: string;
  campaignTitle: string;
  campaignDescription: string;
  electionCandidates: {
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }[];
}

export function ActiveCampaignsContainerComponent() {
  const [activeCampaigns, setActiveCampaigns] = useState<Campaign[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/campaigns/active/vote")
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
            <ActiveCampaignComponent
              key={campaign.electionId}
              campaignType={campaign.campaignType}
              campaignTitle={campaign.campaignTitle}
              campaignDescription={campaign.campaignDescription}
              electionId={campaign.electionId}
              electionCandidates={campaign.electionCandidates}
            />
          );
        })}
      </div>
    </div>
  );
}
