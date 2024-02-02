import useAuth from "../hooks/AuthContext.js";
import { useEffect, useState } from "react";
import Spinner from "react-bootstrap/Spinner";
import styles from "./ActiveCampaignsComponent.module.css";
import UserAuthenticationComponent from "./user/UserAuthenticationComponent.js";
import { VotingActiveCampaignComponent } from "./voting/VotingActiveCampaignComponent";
import { CensusActiveCampaignComponent } from "./census/CensusActiveCampaignComponent";
import { CampaignProps } from "../interfaces/ActiveCampaignsContainerInterface.ts"; //prettier-ignore

export function ActiveCampaignsComponent() {
  const { userPin } = useAuth();
  const [isLoading, setIsLoading] = useState(false);
  const [campaignData, setCampaignData] = useState<CampaignProps[]>([]);

  useEffect(() => {
    const currCampaignData: string | null =
      localStorage.getItem("filteredCampaigns");

    setCampaignData(JSON.parse(currCampaignData!));
  }, [userPin]);

  return !userPin ? (
    <UserAuthenticationComponent />
  ) : (
    <div className={styles.activeCampaignsButtonsGroup}>
      <h2 className={styles.activeCampaignsModalTitle}>
        <b>Активни кампании:</b>
      </h2>
      {isLoading ? (
        <Spinner animation="border" className={styles.spinnerColor} />
      ) : (
        campaignData?.map((campaign) => {
          switch (campaign.campaignType) {
            case "VOTING":
              if ("electionId" in campaign) {
                return (
                  <VotingActiveCampaignComponent
                    key={campaign.campaignTitle}
                    campaignTitle={campaign.campaignTitle}
                    electionId={campaign.electionId}
                  />
                );
              }
            case "CENSUS":
              if ("campaignTitle" in campaign) {
                return (
                  <CensusActiveCampaignComponent
                    key={campaign.campaignTitle}
                    campaignTitle={campaign.campaignTitle}
                    censusId={campaign.campaignId}
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
