import axios from "../../hooks/useAxiosInterceptor.js";
import API_URLS from "../../utils/apiUtils.js";
import Modal from "react-bootstrap/Modal";
import ProgressBar from "react-bootstrap/ProgressBar";
import Button from "react-bootstrap/Button";
import { MouseEvent, useEffect, useState } from "react";
import styles from "./CensusModalComponent.module.css";
import CampaignModalFooterComponent from "../CampaignModalFooterComponent.tsx";
import CensusCategoryInfoComponent from "./CensusCategoryInfoComponent.tsx";
import useAuth from "../../hooks/AuthContext.js";
import { CensusModalProps, UserData } from "../../interfaces/census/CensusModalInterface.ts"; //prettier-ignore

const PERSONAL_INFO_DATA = {
  questionCategory: "PERSONAL",
};

function CensusModalComponent({
  show,
  onHide,
  campaignTitle,
  censusId,
}: CensusModalProps) {
  const { userPin } = useAuth();
  const [campaignDetails, setCampaignDetails] = useState<any>([]);
  const [currentStep, setCurrentStep] = useState(0);
  const [userData, setUserData] = useState<UserData>({
    userPin,
    campaignId: censusId,
    censusAnswers: [],
  });

  const fetchCampaignDetails = async () => {
    try {
      const response = await axios.get(
        `${API_URLS.ACTIVE_CAMPAIGNS}/${censusId}/census`
      );

      setCampaignDetails(response.data);
    } catch (error) {
      console.log("Error fetching regions: ");
    }
  };

  useEffect(() => {
    fetchCampaignDetails();
  }, []);

  const censusCategories = [
    ...new Set(
      (campaignDetails.censusQuestions || []).map(
        (question: { questionCategory: string }) => question.questionCategory
      )
    ),
  ];

  const totalSteps = censusCategories.length;

  const handleContinue = () => {
    if (currentStep < totalSteps - 1) {
      setCurrentStep(currentStep + 1);
    }
  };

  const handleFormSubmit = (event: MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    let location = "";

    axios
      .post(API_URLS.CENSUS, userData, {
        headers: { "Content-Type": "application/json" },
      })
      //@ts-ignore
      .then((response) => {
        if (response.status === 201) {
          location = response.headers.location || "";

          return response.data;
        } else {
          throw new Error(`Server returned ${response.status} status.`);
        }
      })
      //@ts-ignore
      .then((data) => {
        if (data) {
          const successMessage = `Успешно изпращане на данни за преброяване.`;
          alert(successMessage);
        }

        window.location.href = location;
      })
      //@ts-ignore
      .catch((error) => console.error("Error:", error.message));
  };

  const handleBack = () => {
    if (currentStep > 0) {
      setCurrentStep(currentStep - 1);
    }
  };

  const handleInputChange = (
    fieldName: string,
    value: string,
    questionCategory: string
  ) => {
    setUserData((prevData) => {
      const index = prevData.censusAnswers.findIndex(
        (question) =>
          question.questionText === fieldName &&
          question.questionCategory === questionCategory
      );

      if (index !== -1) {
        const updatedAnswers = [...prevData.censusAnswers];
        updatedAnswers[index] = {
          questionText: fieldName,
          answer: value,
          questionCategory,
        };
        return { ...prevData, censusAnswers: updatedAnswers };
      } else {
        return {
          ...prevData,
          censusAnswers: [
            ...prevData.censusAnswers,
            {
              questionText: fieldName,
              answer: value,
              questionCategory,
            },
          ],
        };
      }
    });
  };

  const getCurrentComponent = () => {
    const currentCategory = censusCategories[currentStep];

    const currentQuestions = campaignDetails.censusQuestions?.filter(
      (q: { questionCategory: string }) =>
        q.questionCategory === currentCategory
    );

    switch (currentCategory) {
      case PERSONAL_INFO_DATA.questionCategory:
        return (
          <CensusCategoryInfoComponent
            censusTitle="Лична информация"
            censusQuestions={currentQuestions}
            onInputChange={(field, value, questionCategory) =>
              handleInputChange(field, value, questionCategory)
            }
          />
        );

      case "HOUSING":
        return (
          <CensusCategoryInfoComponent
            censusTitle="Жилищна информация"
            censusQuestions={currentQuestions}
            onInputChange={(field, value, questionCategory) =>
              handleInputChange(field, value, questionCategory)
            }
          />
        );
      case "POPULATION":
        return (
          <CensusCategoryInfoComponent
            censusTitle="Гражданска информация"
            censusQuestions={currentQuestions}
            onInputChange={(field, value, questionCategory) =>
              handleInputChange(field, value, questionCategory)
            }
          />
        );
      case "HEALTH":
        return (
          <CensusCategoryInfoComponent
            censusTitle="Здравна информация"
            censusQuestions={currentQuestions}
            onInputChange={(field, value, questionCategory) =>
              handleInputChange(field, value, questionCategory)
            }
          />
        );
      default:
        return null;
    }
  };

  const progressPercentage = ((currentStep + 1) / totalSteps) * 100;

  return (
    <Modal
      className={styles.censusInfoContainerPosition}
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
        <div className={styles.censusContainer}>
          <h5>{campaignDetails.campaignDescription}</h5>

          <ProgressBar animated now={progressPercentage} />
          <div className={styles.censusInfoContainerPosition}>
            <div className={styles.censusInfoContainer}>
              {getCurrentComponent()}

              <div className={styles.censusModalButtonContainer}>
                <Button
                  className={styles.censusModalButton}
                  disabled={currentStep === 0}
                  onClick={handleBack}
                >
                  ←
                </Button>

                <Button
                  className={styles.censusModalButton}
                  disabled={currentStep === totalSteps - 1}
                  onClick={handleContinue}
                >
                  →
                </Button>
              </div>
            </div>
          </div>
        </div>
      </Modal.Body>

      <Modal.Footer>
        <CampaignModalFooterComponent
          submitButtonDisabled="false"
          buttonText="Изпрати"
          onSubmit={handleFormSubmit}
        />
      </Modal.Footer>
    </Modal>
  );
}

export default CensusModalComponent;
