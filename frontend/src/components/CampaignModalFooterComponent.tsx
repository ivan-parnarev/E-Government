import React from "react";
import Button from "react-bootstrap/Button";
import styles from "./CampaignModalFooterComponent.module.css";
import { CampaignModalFooterProps } from "../interfaces/CampaignModalFooterInterface";

function CampaignModalFooterComponent({
  submitButtonDisabled,
  buttonText,
  onSubmit,
}: CampaignModalFooterProps) {
  return (
    <div className={styles.modalFooterButtonGroup}>
      <Button
        disabled={!submitButtonDisabled}
        className={styles.modalFooterButton}
        onClick={onSubmit}
      >
        {buttonText}
      </Button>
    </div>
  );
}

export default CampaignModalFooterComponent;
