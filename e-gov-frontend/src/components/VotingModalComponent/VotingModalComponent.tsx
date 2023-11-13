import Modal from "react-bootstrap/Modal";
import styles from "./VotingModalComponent.module.css";
import { ChangeEvent, MouseEvent, useState } from "react";
import ElectionRowComponent from "../ElectionRowComponent.tsx";
import PinInputComponent from "../PinInputComponent.tsx";
import ModalFooterComponent from "../ModalFooterComponent.tsx";

interface VotingModalProps {
  show: boolean;
  onHide: () => void;
  campaignTopic: string;
  campaignId: string;
  answersJson: {
    id: string;
    name: string;
    number: string;
  }[];
}

interface UserData {
  pin: string;
  campaignId?: string;
  candidate?: { id: string; name: string; number: string };
}

export function VotingModalComponent({
  show,
  onHide,
  campaignTopic,
  campaignId,
  answersJson,
}: VotingModalProps) {
  const [pinValue, setPinValue] = useState<string>("");
  const [isValidPinValue, setIsValidPinValue] = useState<boolean>(false);
  const [showQuestions, setShowQuestions] = useState<boolean>(false);
  const [checkedId, setCheckedId] = useState<string | null>(null);
  const [userData, setUserData] = useState<UserData | null>(null);

  const validatePinValue = (input: string): boolean => {
    const regex = /^[0-9]+$/;
    return regex.test(input);
  };

  const handlePinChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newPinValue = event.target.value;
    setPinValue(newPinValue);
    setIsValidPinValue(validatePinValue(newPinValue));
  };

  const handleContinue = () => {
    if (pinValue.length < 10) {
      setShowQuestions(false);
    } else if (pinValue.length > 10) {
      setShowQuestions(false);
    } else if (!isValidPinValue) {
      setShowQuestions(false);
    } else {
      setUserData({ pin: pinValue });
      setShowQuestions(true);
    }
  };

  const handleBack = () => {
    setShowQuestions(false);
  };

  const handleCheckboxChange = (id: string, name: string, number: string) => {
    const dataFromChild = {
      campaignId: campaignId,
      candidate: {
        id: id,
        number: number,
        name: name,
      },
    };

    setUserData((prevData) => {
      return { ...(prevData as UserData), ...dataFromChild };
    });

    setCheckedId(id);
  };

  const handleVoteSubmit = (event: MouseEvent<HTMLButtonElement>) => {
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

            window.location.href = response.headers.get("location") || "";
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
            isValidPinValue={isValidPinValue}
            onChange={handlePinChange}
          />
        ) : (
          <>
            <h5>БЮЛЕТИНА ЗА НАРОДНИ ПРЕДСТАВИТЕЛИ</h5>
            <div className={styles.electionRowContainerPosition}>
              <div className={styles.electionRowContainer}>
                {answersJson.map((answer) => {
                  return (
                    <ElectionRowComponent
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
          isValidPinValue={isValidPinValue}
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
