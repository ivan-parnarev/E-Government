import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import styles from "./CreateCensusCampaignComponent.module.css";
import { useEffect, useState } from "react";
import UserAuthenticationComponent from "../user/UserAuthenticationComponent.js";
import CampaignModalFooterComponent from "../CampaignModalFooterComponent";
import { CreateCensusEditAnswersComponent } from "./CreateCensusEditAnswersComponent.js";

async function fetchCampaignData(url) {
  try {
    const response = await fetch(url);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching campaigns:", error);
    return null;
  }
}

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
  const activeCampaignsUrl = "http://localhost:8080/api/v1/campaigns/active";
  const [censusQuestions, setCensusQuestions] = useState([]);
  const [campaignTitle, setCampaignTitle] = useState("");
  const [campaignDescription, setCampaignDescription] = useState("");
  const [campaignStartDate, setCampaignStartDate] = useState("");
  const [campaignEndDate, setCampaignEndDate] = useState("");

  const censusCategories = [
    ...new Set(censusQuestions.map((question) => question.questionCategory)),
  ];

  useEffect(() => {
    const fetchCampaigns = async () => {
      // setIsLoading(true);

      const censusData = await fetchCampaignData(`${activeCampaignsUrl}/census`); // prettier-ignore

      try {
        if (censusData) {
          setCensusQuestions(censusData[0].censusQuestions);
        }
      } catch (error) {
        console.error("Error fetching campaigns:", error);
      } finally {
        // setIsLoading(false);
      }
    };

    fetchCampaigns();
  }, []);

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
      const endDate = new Date(
        new Date(startDate).getTime() + 24 * 60 * 60 * 1000
      );
      setCampaignEndDate(formatDate(endDate));
    }
  };

  const handleEndDateChange = (e) => {
    setCampaignEndDate(e.target.value);
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

  const handleUpdateCensusQuestions = () => {
    const currUserData = {
      ...userData,
      campaignType: "CENSUS",
      campaignTitle,
      campaignDescription,
      campaignStartDate,
      campaignEndDate,
      questions: censusQuestions,
    };

    console.log(currUserData);

    fetch("http://localhost:8080/api/v1/campaigns/create/census", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
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
      .catch((error) =>
        console.error("Error updating census questions:", error)
      );
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
          <div className={styles.censusInfoContainer}>
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

            <CreateCensusEditAnswersComponent
              censusCategories={censusCategories}
              censusQuestions={censusQuestions}
              setCensusQuestions={setCensusQuestions}
            />

            <InputGroup className={styles.createCensusInputDateGroup}>
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
          </div>
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
          onSubmit={handleUpdateCensusQuestions}
          onBack={onBack}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
