import API_URLS from "../utils/apiUtils.js";
import { useEffect, useState } from "react";
import Spinner from "react-bootstrap/Spinner";
import styles from "./ActiveCampaignsComponent.module.css";
import { VotingActiveCampaignComponent } from "./voting/VotingActiveCampaignComponent";
import { CensusActiveCampaignComponent } from "./census/CensusActiveCampaignComponent";
import { CensusCampaignProps, VoteCampaignProps } from "../interfaces/ActiveCampaignsContainerInterface.ts"; //prettier-ignore

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

export function ActiveCampaignsComponent() {
  const [isLoading, setIsLoading] = useState(false);
  const [voteCampaigns, setVoteCampaigns] = useState<VoteCampaignProps[]>([]);
  const [censusCampaigns, setCensusCampaigns] = useState<CensusCampaignProps[]>(
    []
  );

  useEffect(() => {
    const fetchCampaigns = async () => {
      setIsLoading(true);

      const voteData = await fetchCampaignData(API_URLS.ACTIVE_VOTE);
      const censusData = await fetchCampaignData(API_URLS.ACTIVE_CENSUS);

      try {
        if (voteData) {
          setVoteCampaigns(voteData);
        }

        if (censusData) {
          setCensusCampaigns(censusData);
        }
      } catch (error) {
        console.error("Error fetching campaigns:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchCampaigns();
  }, []);

  const activeCampaigns = [...voteCampaigns, ...censusCampaigns];

  return (
    <div className={styles.activeCampaignsButtonsGroup}>
      <h2 className={styles.activeCampaignsModalTitle}>
        <b>Активни кампании:</b>
      </h2>
      {isLoading ? (
        <Spinner animation="border" className={styles.spinnerColor} />
      ) : (
        activeCampaigns.map((campaign) => {
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
                    key={campaign.campaignId}
                    campaignTitle={campaign.campaignTitle}
                    campaignDescription={campaign.campaignDescription}
                    censusId={campaign.campaignId}
                    censusQuestions={campaign.censusQuestions}
                  />
                );
              }
            default:
              break;
          }
        })
      )}
    </div>
  );
}
