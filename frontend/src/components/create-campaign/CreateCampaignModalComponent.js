import { useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/esm/Button";
import styles from "./CreateCampaignModalComponent.module.css";
import { CreateVotingCampaignComponent } from "./CreateVotingCampaignComponent";
import { CreateCensusCampaignComponent } from "./CreateCensusCampaignComponent";

function CreateCampaignModalComponent({ show, onHide }) {
  const [createVotingCampaignModalShow, setCreateVotingCampaignModalShow] =
    useState(false);
  const [createCensusCampaignModalShow, setCreateCensusCampaignModalShow] =
    useState(false);

  const handleVotingModalBack = () => {
    setCreateVotingCampaignModalShow(false);
  };

  const handleCensusModalBack = () => {
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
            onClick={() => setCreateCensusCampaignModalShow(true)}
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
            show={show}
            onHide={() => setCreateVotingCampaignModalShow(false)}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
          />
        )}

        {createCensusCampaignModalShow && (
          <CreateCensusCampaignComponent
            show={show}
            onHide={() => setCreateCensusCampaignModalShow(false)}
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
