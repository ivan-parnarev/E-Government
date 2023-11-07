import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent";
import styles from "./ActiveCampaign.module.css";
import { useState } from "react";

export function ActiveCampaign({ campaignTopic, answersJson }) {
  const [modalShow, setModalShow] = useState(false);

  return (
    <>
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
        answersJson={answersJson}
      />
    </>
  );
}
