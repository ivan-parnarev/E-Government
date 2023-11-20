import { useEffect, useState } from "react";
import { VotingActiveCampaignComponent } from "./voting/VotingActiveCampaignComponent";
import styles from "./ActiveCampaignsContainerComponent.module.css";
import { CensusActiveCampaignComponent } from "./census/CensusActiveCampaignComponent";
import {
  CensusCampaignProps,
  VoteCampaignProps,
} from "../interfaces/ActiveCampaignsContainerInterface";

export function ActiveCampaignsContainerComponent() {
  const [voteCampaigns, setVoteCampaigns] = useState<VoteCampaignProps[]>([]);
  const [censusCampaigns, setCensusCampaigns] = useState<CensusCampaignProps[]>(
    []
  );

  useEffect(() => {
    const fetchVoteData = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/v1/campaigns/active/vote"
        );
        const data = await response.json();

        setVoteCampaigns(data);
      } catch (error) {
        console.error("Error fetching vote campaigns:", error);
      }
    };

    const fetchCensusData = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/v1/campaigns/active/census"
        );
        const data = await response.json();

        setCensusCampaigns([data]);
      } catch (error) {
        console.error("Error fetching census campaigns:", error);
      }
    };

    fetchVoteData();
    fetchCensusData();
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
