import Modal from "react-bootstrap/Modal";
import styles from "./VotingModalComponent.module.css";
import { ChangeEvent, MouseEvent, useState } from "react";
import UserAuthenticationComponent from "../../user/UserAuthenticationComponent.js";
import CampaignModalFooterComponent from "../../CampaignModalFooterComponent.tsx";
import VotingActiveCampaignFormContainer from "../VotingActiveCampaignFormContainer.tsx";
import {
  UserData,
  VotingModalProps,
} from "../../../interfaces/voting/VotingModalInterface.ts";

export function VotingModalComponent({
  show,
  onHide,
  campaignTitle,
  campaignDescription,
  electionId,
  electionCandidates,
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
      setUserData({ userPin: pinValue });
      setShowQuestions(true);
    }
  };

  const handleBack = () => {
    setShowQuestions(false);
  };

  const handleCheckboxChange = (candidateId: string) => {
    const dataFromChild = {
      electionId: electionId,
      candidateId: candidateId,
    };

    setUserData((prevData) => {
      return { ...(prevData as UserData), ...dataFromChild };
    });

    setCheckedId(candidateId);
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
              const successMessage = `Гласувахте успешно!`;
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
            <b>{campaignTitle}</b>
          </h2>
        </Modal.Title>
        <button className={styles.modalHeaderCloseButton} onClick={onHide}>
          ✖
        </button>
      </Modal.Header>

      <Modal.Body className={styles.modalBodyContainer}>
        {!showQuestions ? (
          <UserAuthenticationComponent
            pinValue={pinValue}
            isValidPinValue={isValidPinValue}
            onChange={handlePinChange}
          />
        ) : (
          <VotingActiveCampaignFormContainer
            campaignDescription={campaignDescription}
            electionCandidates={electionCandidates}
            checkedId={checkedId}
            handleCheckboxChange={handleCheckboxChange}
          />
        )}
      </Modal.Body>

      <Modal.Footer>
        <CampaignModalFooterComponent
          showQuestions={showQuestions}
          submitButtonDisabled={checkedId}
          continueButtonDisabled={
            pinValue.length < 10 || pinValue.length > 10 || !isValidPinValue
          }
          buttonText="Гласувай"
          onContinue={handleContinue}
          onBack={handleBack}
          onSubmit={handleVoteSubmit}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
