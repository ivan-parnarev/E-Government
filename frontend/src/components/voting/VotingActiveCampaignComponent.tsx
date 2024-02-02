import axios from "axios";
import API_URLS from "../../utils/apiUtils.js";
import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent/VotingModalComponent.tsx";
import styles from "./VotingActiveCampaignComponent.module.css";
import { useEffect, useState } from "react";
import { VotingActiveCampaignProps } from "../../interfaces/voting/VotingActiveCampaignInterface.ts";

export function VotingActiveCampaignComponent({
  campaignTitle,
  campaignDescription,
  electionId,
}: VotingActiveCampaignProps) {
  const [modalShow, setModalShow] = useState(false);
  const [campaignDetails, setCampaignDetails] = useState<any>([]);

  const fetchCampaignDetails = async () => {
    try {
      const response = await axios.get(
        `${API_URLS.ACTIVE_CAMPAIGNS}/${electionId}/VOTING`
      );

      setCampaignDetails(response.data);
    } catch (error) {
      console.log("Error fetching regions: ");
    }
  };

  useEffect(() => {
    fetchCampaignDetails();
  }, []);

  return (
    <div className={styles.activeCampaignContainer}>
      <Button
        variant="outline-light"
        size="lg"
        className={styles.activeCampaignsButton}
        onClick={() => setModalShow(true)}
      >
        {campaignTitle}
      </Button>

      <VotingModalComponent
        show={modalShow}
        onHide={() => setModalShow(false)}
        campaignTitle={campaignTitle}
        campaignDescription={campaignDescription}
        electionId={electionId}
        electionCandidates={campaignDetails.electionCandidates}
      />
    </div>
  );
}
