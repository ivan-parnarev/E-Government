import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent/VotingModalComponent.tsx";
import styles from "./VotingActiveCampaignComponent.module.css";
import { useState } from "react";

interface VotingActiveCampaignComponent {
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

export function VotingActiveCampaignComponent({
  campaignTitle,
  campaignDescription,
  electionId,
  electionCandidates,
}: VotingActiveCampaignComponent) {
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
        campaignTitle={campaignTitle}
        campaignDescription={campaignDescription}
        electionId={electionId}
        electionCandidates={electionCandidates}
      />
    </div>
  );
}
