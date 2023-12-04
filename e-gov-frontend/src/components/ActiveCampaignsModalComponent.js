import Modal from "react-bootstrap/Modal";
import { ActiveCampaignsComponent } from "./ActiveCampaignsComponent";

function ActiveCampaignsModalComponent({ show, onHide }) {
  return (
    <Modal
      show={show}
      onHide={onHide}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Body>
        <ActiveCampaignsComponent />
      </Modal.Body>
    </Modal>
  );
}

export default ActiveCampaignsModalComponent;
