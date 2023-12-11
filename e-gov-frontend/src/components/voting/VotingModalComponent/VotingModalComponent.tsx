import API_URLS from "../../../utils/apiUtils.js";
import Modal from "react-bootstrap/Modal";
import styles from "./VotingModalComponent.module.css";
import { MouseEvent, useState } from "react";
import UserAuthenticationComponent from "../../user/UserAuthenticationComponent.js";
import CampaignModalFooterComponent from "../../CampaignModalFooterComponent.tsx";
import VotingActiveCampaignFormContainer from "../VotingActiveCampaignFormContainer.tsx";
import usePinInput from "../../../hooks/usePinInput.js";
import { UserData, VotingModalProps } from "../../../interfaces/voting/VotingModalInterface.ts"; //prettier-ignore

export function VotingModalComponent({
  show,
  onHide,
  campaignTitle,
  campaignDescription,
  electionId,
  electionCandidates,
}: VotingModalProps) {
  const { pinValue, isValidPinValue, handlePinChange } = usePinInput();
  const [showQuestions, setShowQuestions] = useState<boolean>(false);
  const [checkedId, setCheckedId] = useState<string | null>(null);
  const [userData, setUserData] = useState<UserData | null>(null);

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

    fetch(API_URLS.VOTE, {
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
