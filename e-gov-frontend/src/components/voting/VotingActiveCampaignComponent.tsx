import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent/VotingModalComponent.tsx";
import styles from "./VotingActiveCampaignComponent.module.css";
import { useState } from "react";
import { VotingActiveCampaignProps } from "../../interfaces/voting/VotingActiveCampaignInterface.ts";

export function VotingActiveCampaignComponent({
  campaignTitle,
  campaignDescription,
  electionId,
  electionCandidates,
}: VotingActiveCampaignProps) {
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
