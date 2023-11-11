import React from "react";
import Button from "react-bootstrap/Button";
import styles from "./ModalFooterComponent.module.css";

export function ModalFooterComponent({
  pinValueLength,
  isValidPinValue,
  showQuestions,
  onContinue,
  onBack,
  onSubmit,
  onHide,
}) {
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
            Гласувай
          </Button>
        </>
      ) : (
        <Button
          className={
            pinValueLength === 10 && isValidPinValue
              ? styles.modalFooterButton
              : styles.disabledModalFooterButton
          }
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
