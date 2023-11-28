import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import styles from "./CreateVotingCampaignComponent.module.css";
import { useState } from "react";
import UserAuthenticationComponent from "../user/UserAuthenticationComponent.js";
import CampaignModalFooterComponent from "../CampaignModalFooterComponent";
import { CreateVotingAddCandidateComponent } from "./CreateVotingAddCandidateComponent.js";

const mockupCensusData = {
  campaignType: "CENSUS",
  campaignTitle: "campaignTitle",
  campaignDescription: "campaignDescr",
  fromUserPin: "1234",
  campaignStartDate: "...",
  campaignEndDate: "...",
  questions: [
    {
      questionText: "Some text",
      questionCategory: "PERSONAL",
      answers: [
        {
          answerText: "some text",
        },
      ],
    },
    {
      questionText: "Another text",
      questionCategory: "HOUSING",
      answers: [
        {
          answerText: "some text",
        },
      ],
    },
  ],
};

export function CreateCensusCampaignComponent({
  userData,
  pinValue,
  isValidPinValue,
  handlePinChange,
  show,
  showQuestions,
  onContinue,
  onBack,
  onHide,
}) {
  const [electionType, setElectionType] = useState("");
  const [campaignTitle, setCampaignTitle] = useState("");
  const [campaignDescription, setCampaignDescription] = useState("");
  const [candidates, setCandidates] = useState([
    { candidateName: "", candidateParty: "", candidateNumber: "" },
  ]);
  const [campaignStartDate, setCampaignStartDate] = useState("");
  const [campaignEndDate, setCampaignEndDate] = useState("");

  const handleElectionTypeChange = (e) => {
    setElectionType(e.target.value);
  };

  const handleCampaignTitleChange = (e) => {
    setCampaignTitle(e.target.value);
  };

  const handleCampaignDescriptionChange = (e) => {
    setCampaignDescription(e.target.value);
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");

    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    const currUserData = {
      ...userData,
      campaignType: "VOTING",
      campaignTitle,
      campaignDescription,
      campaignStartDate,
      campaignEndDate,
      electionType,
      candidates: candidates.slice(0, -1),
    };

    fetch("http://localhost:8080/api/v1/campaigns/create/vote", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(currUserData),
    })
      .then((response) => {
        if (response.status === 201) {
          return response.json().then((data) => {
            if (data) {
              const successMessage = `Създаване на кампанията за гласуване е успешно!`;
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
        <h2>Създаване на кампания за преброяване</h2>
      </Modal.Header>

      <Modal.Body className={styles.createVotingCampaignContainer}>
        {!showQuestions ? (
          <UserAuthenticationComponent
            pinValue={pinValue}
            isValidPinValue={isValidPinValue}
            onChange={handlePinChange}
          />
        ) : (
          <>
            {mockupCensusData.questions.map(
              ({ questionText, questionCategory, answers }) => {
                return (
                  <>
                    <p>{questionText}</p>
                    <p>{questionCategory}</p>
                    {/* <p>questionText</p> */}
                  </>
                );
              }
            )}
          </>
        )}
      </Modal.Body>

      <Modal.Footer>
        <CampaignModalFooterComponent
          pinValueLength={pinValue.length}
          isValidPinValue={isValidPinValue}
          showQuestions={showQuestions}
          continueButtonDisabled={
            pinValue.length < 10 || pinValue.length > 10 || !isValidPinValue
          }
          submitButtonDisabled="false"
          buttonText="Създай"
          onContinue={onContinue}
          onSubmit={handleFormSubmit}
          onBack={onBack}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
