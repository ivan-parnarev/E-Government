import { useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/esm/Button";
import styles from "./CreateCampaignModalComponent.module.css";
import { CreateVotingCampaignComponent } from "./CreateVotingCampaignComponent";

function CreateCampaignModalComponent({ show, onHide }) {
  const [userData, setUserData] = useState(null);
  const [pinValue, setPinValue] = useState("");
  const [isValidPinValue, setIsValidPinValue] = useState(false);
  const [showQuestions, setShowQuestions] = useState(false);
  const [createVotingCampaignModalShow, setCreateVotingCampaignModalShow] =
    useState(false);

  const validatePinValue = (input) => {
    const regex = /^[0-9]+$/;
    return regex.test(input);
  };

  const handlePinChange = (event) => {
    const newPinValue = event.target.value;
    setPinValue(newPinValue);
    setIsValidPinValue(validatePinValue(newPinValue));
  };

  const handleContinue = () => {
    if (pinValue.length < 10) {
      setShowQuestions(false);
    } else if (pinValue.length > 10) {
      setShowQuestions(false);
    } else if (!isValidPinValue) {
      setShowQuestions(false);
    } else {
      setUserData({ creatorUserPin: pinValue });
      setShowQuestions(true);
    }
  };

  const handleVotingModalBack = () => {
    setCreateVotingCampaignModalShow(false);
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
            <b>Създаване на кампания</b>
          </h2>
        </Modal.Title>
        <button
          className={styles.createCampaignHeaderCloseButton}
          onClick={onHide}
        >
          ✖
        </button>
      </Modal.Header>

      <Modal.Body className={styles.createCampaignContainer}>
        <>
          <p>Избери тип на кампанията:</p>

          <Button
            variant="outline-light"
            size="lg"
            className={styles.createCampaignButton}
            // onClick={() => setCreateCampaignModalShow(true)}
          >
            Преброяване
          </Button>

          <Button
            variant="outline-light"
            size="lg"
            className={styles.createCampaignButton}
            onClick={() => setCreateVotingCampaignModalShow(true)}
          >
            Избори
          </Button>
        </>

        {createVotingCampaignModalShow && (
          <CreateVotingCampaignComponent
            userData={userData}
            pinValue={pinValue}
            isValidPinValue={isValidPinValue}
            handlePinChange={handlePinChange}
            show={show}
            showQuestions={showQuestions}
            onContinue={handleContinue}
            onBack={handleVotingModalBack}
            onHide={() => setCreateVotingCampaignModalShow(false)}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
          />
        )}
      </Modal.Body>
    </Modal>
  );
}

export default CreateCampaignModalComponent;
