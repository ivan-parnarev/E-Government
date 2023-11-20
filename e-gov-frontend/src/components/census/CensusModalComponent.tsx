import Modal from "react-bootstrap/Modal";
import ProgressBar from "react-bootstrap/ProgressBar";
import Button from "react-bootstrap/Button";
import { ChangeEvent, MouseEvent, useState } from "react";
import styles from "./CensusModalComponent.module.css";
import PinInputComponent from "../PinInputComponent.tsx";
import CensusModalFooterComponent from "./CensusModalFooterComponent.tsx";
import CensusPersonalInfoComponent from "./CensusPersonalInfoComponent.tsx";

interface CensusModalComponentProps {
  show: boolean;
  onHide: () => void;
  campaignTitle: string;
  campaignDescription: string;
  censusId: string;
  censusQuestions: {
    id: string;
    text: string;
    questionCategory: string;
  }[];
}

interface UserData {
  userPin: string;
  campaignId: string;
  censusAnswers: Array<{
    questionId: string;
    text: string;
    answer: string;
    questionCategory: string;
  }>;
}

function CensusModalComponent({
  show,
  onHide,
  campaignTitle,
  campaignDescription,
  censusId,
  censusQuestions,
}: CensusModalComponentProps) {
  const [currentStep, setCurrentStep] = useState(0);
  const [pinValue, setPinValue] = useState<string>("");
  const [isValidPinValue, setIsValidPinValue] = useState<boolean>(false);
  const [showQuestions, setShowQuestions] = useState<boolean>(false);
  const [userData, setUserData] = useState<UserData>({
    userPin: "",
    campaignId: "",
    censusAnswers: [],
  });

  const validatePinValue = (input: string): boolean => {
    const regex = /^[0-9]+$/;
    return regex.test(input);
  };

  const handlePinChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newPinValue = event.target.value;
    setPinValue(newPinValue);
    setIsValidPinValue(validatePinValue(newPinValue));
  };

  const censusCategories = [
    ...new Set(censusQuestions.map((question) => question.questionCategory)),
  ];

  const totalSteps = censusCategories.length;

  const handleContinue = () => {
    if (currentStep < totalSteps - 1) {
      setCurrentStep(currentStep + 1);
    } else {
      console.log("Census data submitted:", userData);
    }
  };

  const handleFormContinue = () => {
    if (pinValue.length < 10) {
      setShowQuestions(false);
    } else if (pinValue.length > 10) {
      setShowQuestions(false);
    } else if (!isValidPinValue) {
      setShowQuestions(false);
    } else {
      setUserData((prevData) => ({
        ...prevData,
        userPin: pinValue,
        campaignId: censusId,
        censusAnswers: prevData.censusAnswers,
      }));
      setShowQuestions(true);
    }
  };

  const handleFormSubmit = (event: MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    fetch("http://localhost:8080/api/v1/census", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    })
      .then((response) => {
        if (response.status === 201) {
          return response.json().then((data) => {
            if (data) {
              const successMessage = `Успешно изпращане на данни за преброяване.`;
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

  const handleBack = () => {
    if (currentStep > 0) {
      setCurrentStep(currentStep - 1);
    }
  };

  const handleFormBack = () => {
    setShowQuestions(false);
  };

  const handleInputChange = (id: string, fieldName: string, value: string) => {
    setUserData((prevData) => {
      const index = prevData.censusAnswers.findIndex(
        (question) =>
          question.text === fieldName &&
          question.questionCategory === "PERSONAL"
      );

      if (index !== -1) {
        const updatedAnswers = [...prevData.censusAnswers];
        updatedAnswers[index] = {
          questionId: id,
          text: fieldName,
          answer: value,
          questionCategory: "PERSONAL",
        };
        return { ...prevData, censusAnswers: updatedAnswers };
      } else {
        return {
          ...prevData,
          censusAnswers: [
            ...prevData.censusAnswers,
            {
              questionId: id,
              text: fieldName,
              answer: value,
              questionCategory: "PERSONAL",
            },
          ],
        };
      }
    });
  };

  const getCurrentComponent = () => {
    const currentCategory = censusCategories[currentStep];
    const currentQuestions = censusQuestions;

    switch (currentCategory) {
      case "PERSONAL":
        return (
          <CensusPersonalInfoComponent
            censusQuestions={currentQuestions}
            onContinue={handleContinue}
            onInputChange={(id, field, value) =>
              handleInputChange(id, field, value)
            }
          />
        );
      default:
        return null;
    }
  };

  const progressPercentage = (currentStep / totalSteps) * 100;

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
          <div>
            <h5>{campaignDescription}</h5>

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
        )}
      </Modal.Body>

      <Modal.Footer>
        <CensusModalFooterComponent
          pinValueLength={pinValue.length}
          isValidPinValue={isValidPinValue}
          showQuestions={showQuestions}
          onContinue={handleFormContinue}
          onSubmit={handleFormSubmit}
          onBack={handleFormBack}
          onHide={onHide}
        />
      </Modal.Footer>
    </Modal>
  );
}

export default CensusModalComponent;
