import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./VotingModalComponent.module.css";
import { useState } from "react";
import { ElectionRow } from "./ElectionRow";

export function VotingModalComponent({
  show,
  onHide,
  campaignTopic,
  answersJson,
}) {
  const [pinValue, setPinValue] = useState("");
  const [showQuestions, setShowQuestions] = useState(false);
  const [checkedId, setCheckedId] = useState(null);

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
      show={show}
      onHide={onHide}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header>
        <Modal.Title id="contained-modal-title-vcenter">
          <h2>
            <b>{campaignTopic}</b>
          </h2>
        </Modal.Title>
        <button className={styles.modalHeaderCloseButton} onClick={onHide}>
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
            <h5>БЮЛЕТИНА ЗА НАРОДНИ ПРЕДСТАВИТЕЛИ</h5>
            <div className={styles.electionRowContainerPosition}>
              <div className={styles.electionRowContainer}>
                {answersJson.map((answer) => {
                  return (
                    <ElectionRow
                      key={answer.id}
                      id={answer.id}
                      name={answer.name}
                      number={answer.number}
                      checked={checkedId === answer.id}
                      onChange={() => setCheckedId(answer.id)}
                    />
                  );
                })}
              </div>
            </div>
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
        <Button className={styles.modalFooterButton} onClick={onHide}>
          Затвори
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
