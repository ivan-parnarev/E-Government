import React, { MouseEvent } from "react";
import Button from "react-bootstrap/Button";
import styles from "./CensusModalFooterComponent.module.css";

interface CensusModalFooterComponentProps {
  pinValueLength: number;
  isValidPinValue: boolean;
  showQuestions: boolean;
  onContinue: () => void;
  onSubmit: (event: MouseEvent<HTMLButtonElement>) => void;
  onBack: () => void;
  onHide: () => void;
}

function CensusModalFooterComponent({
  pinValueLength,
  isValidPinValue,
  showQuestions,
  onContinue,
  onSubmit,
  onBack,
  onHide,
}: CensusModalFooterComponentProps) {
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
