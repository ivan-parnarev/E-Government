import React from "react";
import Button from "react-bootstrap/Button";
import styles from "./CampaignModalFooterComponent.module.css";
import { CampaignModalFooterProps } from "../interfaces/CampaignModalFooterInterface";

function CampaignModalFooterComponent({
  showQuestions,
  submitButtonDisabled,
  continueButtonDisabled,
  buttonText,
  onContinue,
  onBack,
  onSubmit,
  onHide,
}: CampaignModalFooterProps) {
  return (
    <>
      {showQuestions ? (
        <>
          <div className={styles.modalFooterButtonGroup}>
            <Button className={styles.modalFooterButton} onClick={onBack}>
              Назад
            </Button>
          </div>
          <Button
            disabled={!submitButtonDisabled}
            className={styles.modalFooterButton}
            onClick={onSubmit}
          >
            {buttonText}
          </Button>
        </>
      ) : (
        <Button
          disabled={continueButtonDisabled}
          className={styles.modalFooterButton}
          onClick={onContinue}
        >
          Продължи
        </Button>
      )}
      <Button className={styles.modalFooterButton} onClick={onHide}>
        Затвори
      </Button>
    </>
  );
}

export default CampaignModalFooterComponent;
