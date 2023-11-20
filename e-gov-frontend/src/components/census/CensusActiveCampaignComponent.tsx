import Button from "react-bootstrap/esm/Button";
import styles from "./CensusActiveCampaignComponent.module.css";
import { useState } from "react";
import CensusModalComponent from "./CensusModalComponent.tsx";

interface CensusActiveCampaignComponent {
  campaignTitle: string;
  campaignDescription: string;
  censusId: string;
  censusQuestions: {
    id: string;
    text: string;
    questionCategory: string;
  }[];
}

export function CensusActiveCampaignComponent({
  campaignTitle,
  campaignDescription,
  censusId,
  censusQuestions,
}: CensusActiveCampaignComponent) {
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

      <CensusModalComponent
        show={modalShow}
        onHide={() => setModalShow(false)}
        campaignTitle={campaignTitle}
        campaignDescription={campaignDescription}
        censusId={censusId}
        censusQuestions={censusQuestions}
      />
    </div>
  );
}
