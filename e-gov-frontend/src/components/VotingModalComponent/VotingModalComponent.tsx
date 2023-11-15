import Modal from "react-bootstrap/Modal";
import styles from "./VotingModalComponent.module.css";
import { ChangeEvent, MouseEvent, useState } from "react";
import ActiveCampaignFormContainer from "../ActiveCampaignFormContainer.tsx";
import PinInputComponent from "../PinInputComponent.tsx";
import ModalFooterComponent from "../ModalFooterComponent.tsx";

interface VotingModalProps {
  show: boolean;
  onHide: () => void;
  campaignType: string;
  campaignTitle: string;
  campaignDescription: string;
  electionId: string;
  electionCandidates: {
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }[];
}

interface UserData {
  userPin: string;
  electionId?: string;
  candidate?: { id: string; name: string; number: string };
}

export function VotingModalComponent({
  show,
  onHide,
  campaignType,
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

      <Modal.Body>
        {!showQuestions ? (
          <PinInputComponent
            pinValue={pinValue}
            isValidPinValue={isValidPinValue}
            onChange={handlePinChange}
          />
        ) : (
          <ActiveCampaignFormContainer
            campaignType={campaignType}
            campaignDescription={campaignDescription}
            electionCandidates={electionCandidates}
            checkedId={checkedId}
            handleCheckboxChange={handleCheckboxChange}
          />
        )}
      </Modal.Body>

      <Modal.Footer>
        <ModalFooterComponent
          campaignType={campaignType}
          pinValueLength={pinValue.length}
          isValidPinValue={isValidPinValue}
          showQuestions={showQuestions}
          checkedId={checkedId}
          onContinue={handleContinue}
          onBack={handleBack}
          onSubmit={handleVoteSubmit}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
