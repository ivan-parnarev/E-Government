import axios from "axios";
import API_URLS from "../../utils/apiUtils";
import { formatDate, calculateDefaultEndDate } from "../../utils/dateUtils";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import InputGroup from "react-bootstrap/InputGroup";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import styles from "./CreateVotingCampaignComponent.module.css";
import { useState } from "react";
import useAuth from "../../hooks/AuthContext.js";
import CampaignModalFooterComponent from "../CampaignModalFooterComponent";
import { CreateVotingAddCandidateComponent } from "./CreateVotingAddCandidateComponent.js";
import { CreateVotingReviewCandidatesComponent } from "./CreateVotingReviewCandidatesComponent.js";

const TOTAL_STEPS = 3;

export function CreateVotingCampaignComponent({ show, onHide }) {
  const { userPin } = useAuth();
  const [currentStep, setCurrentStep] = useState(1);
  const [campaignData, setCampaignData] = useState({
    electionType: "",
    campaignTitle: "",
    campaignDescription: "",
    candidates: {},
    campaignStartDate: "",
    campaignEndDate: "",
  });

  const {
    electionType,
    campaignTitle,
    campaignDescription,
    candidates,
    campaignStartDate,
    campaignEndDate,
  } = campaignData;

  const handleElectionTypeChange = (e) => {
    setCampaignData({
      ...campaignData,
      electionType: e.target.value,
    });
  };

  const handleCampaignTitleChange = (e) => {
    setCampaignData({
      ...campaignData,
      campaignTitle: e.target.value,
    });
  };

  const handleCampaignDescriptionChange = (e) => {
    setCampaignData({
      ...campaignData,
      campaignDescription: e.target.value,
    });
  };

  const handleStartDateChange = (e) => {
    const startDate = e.target.value;
    setCampaignData({
      ...campaignData,
      campaignStartDate: startDate,
    });

    if (!campaignEndDate) {
      const endDate = calculateDefaultEndDate(startDate);
      setCampaignData({
        ...campaignData,
        campaignEndDate: formatDate(endDate),
      });
    }
  };

  const handleEndDateChange = (e) => {
    setCampaignData({
      ...campaignData,
      campaignEndDate: e.target.value,
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    let location = "";

    const currUserData = {
      creatorUserPin: userPin,
      campaignType: "VOTING",
      ...campaignData,
    };

    axios
      .post(API_URLS.CREATE_VOTE, currUserData, {
        headers: { "Content-Type": "application/json" },
      })
      .then((response) => {
        if (response.status === 201) {
          location = response.headers.get("location");

          return response.data;
        }
      })
      .then((data) => {
        console.log(data);
        if (data) {
          const successMessage = `${data.message} `;
          alert(successMessage);
        }

        window.location.href = location;
      })
      .catch((error) => console.error("Error:", error.message));
  };

  const handleContinue = () => {
    if (currentStep < TOTAL_STEPS) {
      if (validateStep(currentStep)) {
        setCurrentStep(currentStep + 1);
      }
    }
  };

  const validateStep = (step) => {
    return true;
  };

  const handleBack = () => {
    if (currentStep > 1) {
      setCurrentStep(currentStep - 1);
    }
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
        <h2>Създаване на кампания за избори</h2>
      </Modal.Header>

      <Modal.Body className={styles.createVotingCampaignContainer}>
        {currentStep === 1 && (
          <>
            <FloatingLabel
              label="Тип на изборната кампания:"
              className={styles.createVotingCampaignInputGroup}
            >
              <Form.Select
                aria-label="Floating label select example"
                onChange={handleElectionTypeChange}
                value={electionType}
              >
                <option></option>
                <option value="MAYOR">Местни избори</option>
                <option value="PRESIDENT">Президентски избори</option>
                <option value="PARLIAMENT">Парламентарни избори</option>
                <option value="COUNCIL">Изброи за Европейски парламент</option>
              </Form.Select>
            </FloatingLabel>

            <FloatingLabel label="Име на кампанията:" className="mb-3">
              <Form.Control
                type="text"
                placeholder=""
                onChange={handleCampaignTitleChange}
                value={campaignTitle}
              />
            </FloatingLabel>

            <FloatingLabel label="Описание на кампанията:" className="mb-3">
              <Form.Control
                type="text"
                placeholder=""
                onChange={handleCampaignDescriptionChange}
                value={campaignDescription}
              />
            </FloatingLabel>

            <InputGroup
              className={styles.createVotingCampaignCandidateInputDateGroup}
            >
              <Row>
                <p className={styles.createVotingCampaignInputGroupInputLabel}>
                  Избери начало и край на кампанията:
                </p>
              </Row>
              <Row>
                <Col>
                  <FloatingLabel label="Начална дата">
                    <input
                      type="datetime-local"
                      className="form-control"
                      value={formatDate(campaignStartDate)}
                      onChange={handleStartDateChange}
                    />
                  </FloatingLabel>
                </Col>

                <Col>
                  <FloatingLabel label="Крайна дата">
                    <input
                      type="datetime-local"
                      className="form-control"
                      value={formatDate(campaignEndDate)}
                      onChange={handleEndDateChange}
                    />
                  </FloatingLabel>
                </Col>
              </Row>
            </InputGroup>
          </>
        )}

        {currentStep === 2 && (
          <CreateVotingAddCandidateComponent
            electionType={electionType}
            candidates={candidates}
            setCandidates={(updatedCandidates) =>
              setCampaignData({
                ...campaignData,
                candidates: updatedCandidates,
              })
            }
          />
        )}

        {currentStep === 3 && (
          <CreateVotingReviewCandidatesComponent campaignData={campaignData} />
        )}

        <div className={styles.censusModalButtonContainer}>
          <Button
            className={styles.censusModalButton}
            disabled={currentStep === 1}
            onClick={handleBack}
          >
            ←
          </Button>

          <Button
            className={styles.censusModalButton}
            disabled={currentStep === 3}
            onClick={handleContinue}
          >
            →
          </Button>
        </div>
      </Modal.Body>

      <Modal.Footer>
        <CampaignModalFooterComponent
          submitButtonDisabled="false"
          buttonText="Създай"
          onSubmit={handleFormSubmit}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
