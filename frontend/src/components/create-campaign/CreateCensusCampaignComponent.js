import axios from "axios";
import API_URLS from "../../utils/apiUtils";
import { formatDate, calculateDefaultEndDate } from "../../utils/dateUtils";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import styles from "./CreateCensusCampaignComponent.module.css";
import { useEffect, useState } from "react";
import useAuth from "../../hooks/AuthContext.js";
import CampaignModalFooterComponent from "../CampaignModalFooterComponent";
import { CreateCensusEditAnswersComponent } from "./CreateCensusEditAnswersComponent.js";

async function fetchCampaignData(url) {
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    console.error("Error fetching campaigns:", error);
    return null;
  }
}

export function CreateCensusCampaignComponent({ show, onHide }) {
  const { userPin } = useAuth();
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
      const censusData = await fetchCampaignData(API_URLS.ACTIVE_CENSUS);

      try {
        if (censusData) {
          setCensusQuestions(censusData[0].censusQuestions);
        }
      } catch (error) {
        console.error("Error fetching campaigns:", error);
      } finally {
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
      const endDate = calculateDefaultEndDate(startDate);
      setCampaignEndDate(formatDate(endDate));
    }
  };

  const handleEndDateChange = (e) => {
    setCampaignEndDate(e.target.value);
  };

  const handleUpdateCensusQuestions = () => {
    let location = "";

    const currUserData = {
      creatorUserPin: userPin,
      campaignType: "CENSUS",
      campaignTitle,
      campaignDescription,
      campaignStartDate,
      campaignEndDate,
      questions: censusQuestions,
    };

    axios
      .post(API_URLS.CREATE_CENSUS, currUserData, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        if (response.status === 201) {
          location = response.headers.get("location") || "";

          return response.data;
        } else {
          throw new Error(`Server returned ${response.status} status.`);
        }
      })
      .then((data) => {
        if (data) {
          const successMessage = `${data.message} `;
          alert(successMessage);
        }

        window.location.href = location;
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
      </Modal.Body>

      <Modal.Footer>
        <CampaignModalFooterComponent
          submitButtonDisabled="false"
          buttonText="Създай"
          onSubmit={handleUpdateCensusQuestions}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}
