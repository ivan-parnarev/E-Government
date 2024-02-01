import axios from "../../../hooks/useAxiosInterceptor.js";
import API_URLS from "../../../utils/apiUtils.js";
import Modal from "react-bootstrap/Modal";
import styles from "./VotingModalComponent.module.css";
import { MouseEvent, useState } from "react";
import CampaignModalFooterComponent from "../../CampaignModalFooterComponent.tsx";
import VotingActiveCampaignFormContainer from "../VotingActiveCampaignFormContainer.tsx";
import useAuth from "../../../hooks/AuthContext.js";
import { UserData, VotingModalProps } from "../../../interfaces/voting/VotingModalInterface.ts"; //prettier-ignore

export function VotingModalComponent({
  show,
  onHide,
  campaignTitle,
  campaignDescription,
  electionId,
  electionCandidates,
}: VotingModalProps) {
  const { userPin } = useAuth();
  const [checkedId, setCheckedId] = useState<string | null>(null);
  const [userData, setUserData] = useState<UserData | null>(null);

  const handleCheckboxChange = (candidateId: string) => {
    const dataFromChild = {
      electionId: electionId,
      candidateId: candidateId,
    };

    setUserData(() => {
      return { userPin, ...dataFromChild };
    });

    setCheckedId(candidateId);
  };

  const handleVoteSubmit = (event: MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    let location = "";

    console.log(userData);

    axios
      .post(API_URLS.VOTE, userData, {
        headers: { "Content-Type": "application/json" },
      })
      //@ts-ignore
      .then((response) => {
        if (response.status === 201) {
          location = response.headers.location || "";

          return response.data;
        }
      })
      //@ts-ignore
      .then((data) => {
        if (data) {
          const successMessage = `${data.message} `;
          alert(successMessage);
        }

        window.location.href = location;
      })
      //@ts-ignore
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
        <VotingActiveCampaignFormContainer
          campaignDescription={campaignDescription}
          electionCandidates={electionCandidates}
          checkedId={checkedId}
          handleCheckboxChange={handleCheckboxChange}
        />
      </Modal.Body>

      <Modal.Footer>
        <CampaignModalFooterComponent
          submitButtonDisabled={checkedId}
          buttonText="Гласувай"
          onSubmit={handleVoteSubmit}
        />
      </Modal.Footer>
    </Modal>
  );
}
