import API_URLS from "../../utils/apiUtils";
import { formatDate, calculateDefaultEndDate } from "../../utils/dateUtils";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import styles from "./CreateVotingCampaignComponent.module.css";
import { useState } from "react";
import useAuth from "../../hooks/AuthContext.js";
import CampaignModalFooterComponent from "../CampaignModalFooterComponent";
import { CreateVotingAddCandidateComponent } from "./CreateVotingAddCandidateComponent.js";

export function CreateVotingCampaignComponent({ show, onHide }) {
  const { userPin } = useAuth();
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

  const handleStartDateChange = (e) => {
    const startDate = e.target.value;
    setCampaignStartDate(startDate);

    if (!campaignEndDate) {
      const endDate = calculateDefaultEndDate(startDate);
      setCampaignEndDate(formatDate(endDate));
    }
  };

  const handleEndDateChange = (e) => {
    setCampaignEndDate(e.target.value);
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    const currUserData = {
      creatorUserPin: userPin,
      campaignType: "VOTING",
      campaignTitle,
      campaignDescription,
      campaignStartDate,
      campaignEndDate,
      electionType,
      candidates: candidates.slice(0, -1),
    };

    fetch(API_URLS.CREATE_VOTE, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(currUserData),
    })
      .then((response) => {
        if (response.status === 201) {
          return response.json().then((data) => {
            if (data) {
              const successMessage = `${data.message} `;
              alert(successMessage);
            }

            window.location.href = response.headers.get("location") || "";
          });
        } else {
          return response.json().then((errorData) => {
            console.error("Error:", errorData);
          });
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
        <h2>Създаване на кампания за избори</h2>
      </Modal.Header>

      <Modal.Body className={styles.createVotingCampaignContainer}>
        <>
          <FloatingLabel
            label="Тип на изборната кампания:"
            className={styles.createVotingCampaignInputGroup}
          >
            <Form.Select
              aria-label="Floating label select example"
              onChange={handleElectionTypeChange}
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
            />
          </FloatingLabel>

          <FloatingLabel label="Описание на кампанията:" className="mb-3">
            <Form.Control
              type="text"
              placeholder=""
              onChange={handleCampaignDescriptionChange}
            />
          </FloatingLabel>

          <CreateVotingAddCandidateComponent
            candidates={candidates}
            setCandidates={setCandidates}
          />

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
        {/* )} */}
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
