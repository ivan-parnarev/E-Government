import React from "react";
import Button from "react-bootstrap/Button";
import styles from "./CampaignModalFooterComponent.module.css";
import { CampaignModalFooterProps } from "../interfaces/CampaignModalFooterInterface";

function CampaignModalFooterComponent({
  submitButtonDisabled,
  buttonText,
  onSubmit,
  onHide,
}: CampaignModalFooterProps) {
  return (
    <>
      <>
        <div className={styles.modalFooterButtonGroup}>
          <Button className={styles.modalFooterButton}>Назад</Button>
        </div>
        <Button
          disabled={!submitButtonDisabled}
          className={styles.modalFooterButton}
          onClick={onSubmit}
        >
          {buttonText}
        </Button>
      </>

      <Button className={styles.modalFooterButton} onClick={onHide}>
        Затвори
      </Button>
    </>
  );
}

export default CampaignModalFooterComponent;
