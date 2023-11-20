import React from "react";
import Button from "react-bootstrap/Button";
import styles from "./VotingModalFooterComponent.module.css";
import { VotingModalFooterProps } from "../../interfaces/voting/VotingModalFooterInterface";

function VotingModalFooterComponent({
  pinValueLength,
  isValidPinValue,
  showQuestions,
  checkedId,
  onContinue,
  onBack,
  onSubmit,
  onHide,
}: VotingModalFooterProps) {
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
            disabled={!checkedId}
            className={styles.modalFooterButton}
            onClick={onSubmit}
          >
            Гласувай
          </Button>
        </>
      ) : (
        <Button
          disabled={
            pinValueLength < 10 || pinValueLength > 10 || !isValidPinValue
          }
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

export default VotingModalFooterComponent;
