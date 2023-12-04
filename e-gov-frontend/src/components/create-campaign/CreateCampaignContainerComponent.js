import { useState } from "react";
import Button from "react-bootstrap/esm/Button";
import styles from "./CreateCampaignContainerComponent.module.css";
import CreateCampaignModalComponent from "./CreateCampaignModalComponent";

export function CreateCampaignContainerComponent() {
  const [createCampaignModalShow, setCreateCampaignModalShow] = useState(false);

  return (
    <div>
      <Button
        variant="outline-light"
        size="lg"
        className={styles.createCampaignButton}
        onClick={() => setCreateCampaignModalShow(true)}
      >
        Създай кампания
      </Button>

      <CreateCampaignModalComponent
        show={createCampaignModalShow}
        onHide={() => setCreateCampaignModalShow(false)}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      />
    </div>
  );
}
