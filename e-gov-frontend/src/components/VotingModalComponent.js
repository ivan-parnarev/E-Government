import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./VotingModalComponent.module.css";
import { useState } from "react";
import { ElectionRow } from "./ElectionRow";

export function VotingModalComponent(props) {
  const [pinValue, setPinValue] = useState("");
  const [showQuestions, setShowQuestions] = useState(false);

  const handlePinChange = (event) => {
    setPinValue(event.target.value);
  };

  const handleContinue = () => {
    setShowQuestions(true);
  };

  const handleBack = () => {
    setShowQuestions(false);
  };

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header>
        <Modal.Title id="contained-modal-title-vcenter">
          Електронно гласуване
        </Modal.Title>
        <button
          className={styles.modalHeaderCloseButton}
          onClick={props.onHide}
        >
          ✖
        </button>
      </Modal.Header>
      <Modal.Body>
        {!showQuestions ? (
          <>
            <h4>Влезли сте като гост</h4>
            <p>Моля въведете ЕГН, за да се идентифицирате:</p>
            <InputGroup size="sm" className="mb-3">
              <InputGroup.Text id="inputGroup-sizing-sm">ЕГН:</InputGroup.Text>
              <Form.Control
                aria-label="Small"
                aria-describedby="inputGroup-sizing-sm"
                value={pinValue}
                onChange={handlePinChange}
              />
            </InputGroup>
          </>
        ) : (
          <>
            <h4>Район Видин</h4>
            <h2>
              <b>ИЗБОРИ ЗА КМЕТ НА ОБЩИНА</b>
            </h2>
            <ElectionRow />
          </>
        )}
      </Modal.Body>
      <Modal.Footer>
        {!showQuestions ? (
          <Button className={styles.modalFooterButton} onClick={handleContinue}>
            Продължи
          </Button>
        ) : (
          <>
            <div className={styles.modalFooterButtonGroup}>
              <Button className={styles.modalFooterButton} onClick={handleBack}>
                Назад
              </Button>
            </div>
            <Button className={styles.modalFooterButton}>Гласувай</Button>
          </>
        )}
        <Button className={styles.modalFooterButton} onClick={props.onHide}>
          Затвори
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
