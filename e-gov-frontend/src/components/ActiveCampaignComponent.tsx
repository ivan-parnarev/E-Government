import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent/VotingModalComponent.tsx";
import styles from "./ActiveCampaignComponent.module.css";
import { useState } from "react";

interface ActiveCampaignProps {
  campaignType: string;
  campaignTitle: string;
  campaignDescription: string;
  electionId: string;
  electionCandidates: {
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }[];
}

export function ActiveCampaignComponent({
  campaignType,
  campaignTitle,
  campaignDescription,
  electionId,
  electionCandidates,
}: ActiveCampaignProps) {
  const [modalShow, setModalShow] = useState(false);

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
        campaignType={campaignType}
        campaignTitle={campaignTitle}
        campaignDescription={campaignDescription}
        electionId={electionId}
        electionCandidates={electionCandidates}
      />
    </div>
  );
}
