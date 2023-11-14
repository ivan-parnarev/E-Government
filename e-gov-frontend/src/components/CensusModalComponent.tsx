import ProgressBar from "react-bootstrap/ProgressBar";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import styles from "./CensusModalComponent.module.css";
import CensusDemographicInfoComponent from "./CensusDemographicInfoComponent.tsx";
import CensusEducationInfoComponent from "./CensusEductionInfoComponent.tsx";

interface CensusModalComponentProps {
  censusCategories: string[];
  censusData: Record<string, any>[];
}

function CensusModalComponent({
  censusCategories,
  censusData,
}: CensusModalComponentProps) {
  const [currentStep, setCurrentStep] = useState(0);
  const [userData, setUserData] = useState({});

  const totalSteps = censusCategories.length;

  const handleContinue = () => {
    if (currentStep < totalSteps - 1) {
      setCurrentStep(currentStep + 1);
    } else {
      console.log("Census data submitted:", userData);
    }
  };

  const handleBack = () => {
    if (currentStep > 0) {
      setCurrentStep(currentStep - 1);
    }
  };

  const handleInputChange = (fieldName: string, value: string) => {
    setUserData({ ...userData, [fieldName]: value });
  };

  const getCurrentComponent = () => {
    const currentCategory = censusCategories[currentStep];
    const currentQuestions = censusData[currentStep][1];

    switch (currentCategory) {
      case "demographicInfo":
        return (
          <CensusDemographicInfoComponent
            censusQuestions={currentQuestions}
            onContinue={handleContinue}
            onInputChange={(field, value) => handleInputChange(field, value)}
          />
        );
      case "educationInfo":
        return (
          <CensusEducationInfoComponent
            censusQuestions={currentQuestions}
            onContinue={handleContinue}
            onInputChange={(field, value) => handleInputChange(field, value)}
          />
        );

      default:
        return null;
    }
  };

  const progressPercentage = (currentStep / totalSteps) * 100;

  return (
    <div>
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
  );
}

export default CensusModalComponent;
