import React from "react";
import Button from "react-bootstrap/Button";
import styles from "./CensusModalFooterComponent.module.css";
import { CensusModalFooterProps } from "../../interfaces/census/CensusModalFooterInterface";

function CensusModalFooterComponent({
  pinValueLength,
  isValidPinValue,
  showQuestions,
  onContinue,
  onSubmit,
  onBack,
  onHide,
}: CensusModalFooterProps) {
  return (
    <>
      {showQuestions ? (
        <>
          <div className={styles.modalFooterButtonGroup}>
            <Button className={styles.modalFooterButton} onClick={onBack}>
              Назад
            </Button>
          </div>

          <Button className={styles.modalFooterButton} onClick={onSubmit}>
            Изпрати
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

export default CensusModalFooterComponent;
