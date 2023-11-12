import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent";
import styles from "./ActiveCampaignComponent.module.css";
import { useState } from "react";

export function ActiveCampaign({ campaignTopic, campaignId, answersJson }) {
  const [modalShow, setModalShow] = useState(false);

  return (
    <div className={styles.activeCampaignContainer}>
      <Button
        variant="outline-light"
        size="lg"
        className={styles.activeCampaignsButton}
        onClick={() => setModalShow(true)}
      >
        {campaignTopic}
      </Button>

      <VotingModalComponent
        show={modalShow}
        onHide={() => setModalShow(false)}
        campaignTopic={campaignTopic}
        campaignId={campaignId}
        answersJson={answersJson}
      />
    </div>
  );
}
