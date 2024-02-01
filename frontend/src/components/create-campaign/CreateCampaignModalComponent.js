import { useState } from "react";
import Button from "react-bootstrap/esm/Button";
import styles from "./CreateCampaignModalComponent.module.css";
import { CreateVotingCampaignComponent } from "./voting/CreateVotingCampaignComponent";
import { CreateCensusCampaignComponent } from "./census/CreateCensusCampaignComponent";

function CreateCampaignModalComponent() {
  const [createVotingCampaignModalShow, setCreateVotingCampaignModalShow] =
    useState(false);
  const [createCensusCampaignModalShow, setCreateCensusCampaignModalShow] =
    useState(false);

  return (
    <div className={styles.createCampaignContainer}>
      <>
        <p className={styles.title}>Избери тип на кампанията:</p>

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
          show={createVotingCampaignModalShow}
          onHide={() => setCreateVotingCampaignModalShow(false)}
          size="lg"
          aria-labelledby="contained-modal-title-vcenter"
          centered
        />
      )}

      {createCensusCampaignModalShow && (
        <CreateCensusCampaignComponent
          show={createCensusCampaignModalShow}
          onHide={() => setCreateCensusCampaignModalShow(false)}
          size="lg"
          aria-labelledby="contained-modal-title-vcenter"
          centered
        />
      )}
    </div>
  );
}

export default CreateCampaignModalComponent;
