import { useState } from "react";
import Button from "react-bootstrap/esm/Button";
import styles from "./ActiveCampaignsContainerComponent.module.css";
import ActiveCampaignsModalComponent from "./ActiveCampaignsModalComponent.js";

export function ActiveCampaignsContainerComponent() {
  const [activeCampaignsModalShow, setActiveCampaignsModalShow] =
    useState(false);

  return (
    <div className={styles.activeCampaignsSection}>
      <Button
        variant="outline-light"
        size="lg"
        className={styles.activeCampaignsButton}
        onClick={() => setActiveCampaignsModalShow(true)}
      >
        Активни кампании
      </Button>

      <ActiveCampaignsModalComponent
        show={activeCampaignsModalShow}
        onHide={() => setActiveCampaignsModalShow(false)}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      />
    </div>
  );
}
