import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./VotingModalComponent.module.css";
import { useState } from "react";
import { ElectionRow } from "./ElectionRow";
import { PinInputComponent } from "./PinInputComponent";
import ModalFooterComponent from "./ModalFooterComponent";

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
        console.log(response.headers.get("location"));
        if (response.status === 201) {
          return response.json().then((data) => {
            if (data) {
              const successMessage = `Успешно гласувахте за кандидат ${data.candidate.number}. ${data.candidate.name}!`;
              alert(successMessage);
            }

            window.location.href = response.headers.get("location");
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
          <PinInputComponent
            pinValue={pinValue}
            onChange={handlePinChange}
            onFocus={handleFocus}
            isFocused={isFocused}
          />
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
        <ModalFooterComponent
          pinValueLength={pinValue.length}
          showQuestions={showQuestions}
          onContinue={handleContinue}
          onBack={handleBack}
          onSubmit={handleVoteSubmit}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
