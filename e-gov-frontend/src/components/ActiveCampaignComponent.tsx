import Button from "react-bootstrap/esm/Button";
import { VotingModalComponent } from "./VotingModalComponent/VotingModalComponent.tsx";
import styles from "./ActiveCampaignComponent.module.css";
import { useState } from "react";

interface ActiveCampaignProps {
  campaignType: string;
  campaignTopic: string;
  campaignId: string;
  answersJson: {
    id: string;
    name: string;
    number: string;
  }[];
}

export function ActiveCampaignComponent({
  campaignType,
  campaignTopic,
  campaignId,
  answersJson,
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
        {campaignTopic}
      </Button>

      <VotingModalComponent
        show={modalShow}
        onHide={() => setModalShow(false)}
        campaignType={campaignType}
        campaignTopic={campaignTopic}
        campaignId={campaignId}
        answersJson={answersJson}
      />
    </div>
  );
}
