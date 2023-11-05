import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./VotingModalComponent.module.css";

export function VotingModalComponent(props) {
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header>
        <Modal.Title id="contained-modal-title-vcenter">
          Електронно гласуване
        </Modal.Title>
        <button
          className={styles.modalHeaderCloseButton}
          onClick={props.onHide}
        >
          ✖
        </button>
      </Modal.Header>
      <Modal.Body>
        <h4>Влезли сте като гост</h4>
        <p>Моля въведете ЕГН, за да се идентифицирате:</p>
        <InputGroup size="sm" className="mb-3">
          <InputGroup.Text id="inputGroup-sizing-sm">ЕГН:</InputGroup.Text>
          <Form.Control
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
          />
        </InputGroup>
      </Modal.Body>
      <Modal.Footer>
        <Button className={styles.modalFooterCloseButton}>Продължи</Button>
        <Button
          onClick={props.onHide}
          className={styles.modalFooterCloseButton}
        >
          Затвори
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
