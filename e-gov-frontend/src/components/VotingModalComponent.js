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
  campaignId,
  answersJson,
}) {
  const [pinValue, setPinValue] = useState("");
  const [isValidPinValue, setIsValidPinValue] = useState(false);
  const [isFocused, setIsFocused] = useState(false);
  const [showQuestions, setShowQuestions] = useState(false);
  const [checkedId, setCheckedId] = useState(null);
  const [userData, setUserData] = useState(null);
  const [successMessage, setSuccessMessage] = useState("");

  const validatePinValue = (input) => {
    const regex = /^[0-9]{10}$/gm;
    return regex.test(input);
  };

  const handlePinChange = (event) => {
    setPinValue(event.target.value);
    setIsValidPinValue(validatePinValue(pinValue));
  };

  const handleContinue = () => {
    setUserData({ pin: pinValue });

    if (pinValue.length < 10) {
      setShowQuestions(false);
    } else if (pinValue.length > 10) {
      setShowQuestions(false);
    } else {
      setShowQuestions(true);
    }
  };

  const handleBack = () => {
    setShowQuestions(false);
  };

  const handleFocus = () => {
    setIsFocused(true);
  };

  const handleCheckboxChange = (id, name, number) => {
    const dataFromChild = {
      campaignId: campaignId,
      candidate: {
        id: id,
        number: number,
        name: name,
      },
    };
    setUserData((prevData) => {
      return { ...prevData, ...dataFromChild };
    });

    setCheckedId(id);
  };

  const handleVoteSubmit = (event) => {
    event.preventDefault();

    fetch("http://localhost:8080/api/v1/vote", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    })
      .then((response) => {
        if (response.status === 201) {
          return response.json().then((data) => {
            if (data) {
              const successMessage = `Успешно гласувахте за кандидат ${data.candidate.number}. ${data.candidate.name}!`;
              alert(successMessage);
            }

            window.location.href = "http://localhost:3000/active-campaigns";
          });
        } else {
          return response.json();
        }
      })
      .catch((error) => console.error("Error:", error.message));
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
                onFocus={handleFocus}
              />
            </InputGroup>
            {pinValue.length < 10 && isFocused ? (
              <p className={styles.invalidInput}>
                Въведеното ЕГН съдържа по-малко от 10 цифри.
              </p>
            ) : (
              ""
            )}
            {pinValue.length > 10 ? (
              <p className={styles.invalidInput}>
                Въведеното ЕГН съдържа по-повече от 10 цифри.
              </p>
            ) : (
              ""
            )}
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
                      onChange={handleCheckboxChange}
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
          <Button
            className={`${
              pinValue.length === 10
                ? styles.modalFooterButton
                : styles.disabledModalFooterButton
            }`}
            onClick={handleContinue}
          >
            Продължи
          </Button>
        ) : (
          <>
            <div className={styles.modalFooterButtonGroup}>
              <Button className={styles.modalFooterButton} onClick={handleBack}>
                Назад
              </Button>
            </div>
            <Button
              className={styles.modalFooterButton}
              onClick={handleVoteSubmit}
            >
              Гласувай
            </Button>
          </>
        )}
        <Button className={styles.modalFooterButton} onClick={onHide}>
          Затвори
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
