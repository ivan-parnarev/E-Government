import { useEffect, useState } from "react";
import { VotingActiveCampaignComponent } from "./voting/VotingActiveCampaignComponent";
import styles from "./ActiveCampaignsContainerComponent.module.css";
import { CensusActiveCampaignComponent } from "./census/CensusActiveCampaignComponent";
import {
  CensusCampaignProps,
  VoteCampaignProps,
} from "../interfaces/ActiveCampaignsContainerInterface";

async function fetchCampaignData(url: string): Promise<any> {
  try {
    const response = await fetch(url);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching campaigns:", error);
    return null;
  }
}

export function ActiveCampaignsContainerComponent() {
  const activeCampaignsUrl = "http://localhost:8080/api/v1/campaigns/active";
  const [voteCampaigns, setVoteCampaigns] = useState<VoteCampaignProps[]>([]);
  const [censusCampaigns, setCensusCampaigns] = useState<CensusCampaignProps[]>(
    []
  );

  useEffect(() => {
    const fetchCampaigns = async () => {
      const voteData = await fetchCampaignData(`${activeCampaignsUrl}/vote`);
      const censusData = await fetchCampaignData(
        `${activeCampaignsUrl}/census`
      );

      if (voteData) {
        setVoteCampaigns(voteData);
      }

      if (censusData) {
        setCensusCampaigns([censusData]);
      }
    };

    fetchCampaigns();
  }, []);

  const activeCampaigns = [...voteCampaigns, ...censusCampaigns];

  return (
    <div className={styles.activeCampaignsSection}>
      <h2 className={styles.subTitle}>Активни кампании:</h2>
      <div className={styles.activeCampaignsButtonsGroup}>
        {activeCampaigns.map((campaign) => {
          switch (campaign.campaignType) {
            case "VOTING":
              if ("electionCandidates" in campaign) {
                return (
                  <VotingActiveCampaignComponent
                    key={campaign.electionId}
                    campaignTitle={campaign.campaignTitle}
                    campaignDescription={campaign.campaignDescription}
                    electionId={campaign.electionId}
                    electionCandidates={campaign.electionCandidates}
                  />
                );
              }
            case "CENSUS":
              if ("censusQuestions" in campaign) {
                return (
                  <CensusActiveCampaignComponent
                    key={campaign.id}
                    campaignTitle={campaign.campaignTitle}
                    campaignDescription={campaign.campaignDescription}
                    censusId={campaign.id}
                    censusQuestions={campaign.censusQuestions}
                  />
                );
              }
            default:
              break;
          }
        })}
      </div>
    </div>
  );
}
