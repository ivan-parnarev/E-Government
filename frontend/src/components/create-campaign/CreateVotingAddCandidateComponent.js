import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./CreateVotingAddCandidateComponent.module.css";
import { useState } from "react";

export function CreateVotingAddCandidateComponent({
  candidates,
  setCandidates,
}) {
  const [addButtonDisabled, setAddButtonDisabled] = useState(true);

  const handleCandidateChange = (index, field, value) => {
    const newCandidates = [...candidates];
    newCandidates[index][field] = value;
    setCandidates(newCandidates);

    const isLastCandidateEmpty =
      candidates.length > 0 &&
      (candidates[candidates.length - 1].candidateParty === "" ||
        candidates[candidates.length - 1].candidateNumber === "" ||
        candidates[candidates.length - 1].candidateName === "");

    if (isLastCandidateEmpty) {
      setAddButtonDisabled(true);
    }

    if (!isLastCandidateEmpty) {
      setAddButtonDisabled(false);
    }
  };

  const addCandidate = () => {
    setAddButtonDisabled(true);

    const isLastCandidateEmpty =
      candidates.length > 0 &&
      (candidates[candidates.length - 1].candidateParty === "" ||
        candidates[candidates.length - 1].candidateNumber === "" ||
        candidates[candidates.length - 1].candidateName === "");

    if (isLastCandidateEmpty) {
      return;
    }

    setCandidates([
      ...candidates,
      { candidateName: "", candidateParty: "", candidateNumber: "" },
    ]);
  };

  const removeCandidate = (index) => {
    const newCandidates = [...candidates];
    newCandidates.splice(index, 1);
    setCandidates(newCandidates);
  };

  return (
    <InputGroup className={styles.createVotingCampaignInputGroup}>
      <p className={styles.createVotingCampaignInputGroupInputLabel}>
        Кандидати:
      </p>
      {candidates.map((candidate, index) => (
        <Row key={index} className={styles.createVotingCampaignCandidate}>
          <Col>
            <Form.Control
              placeholder="Име на партия"
              value={candidate.candidateParty}
              onChange={(e) =>
                handleCandidateChange(index, "candidateParty", e.target.value)
              }
            />
          </Col>
          <Col>
            <Form.Control
              placeholder="Номер на кандидат"
              value={candidate.candidateNumber}
              onChange={(e) =>
                handleCandidateChange(index, "candidateNumber", e.target.value)
              }
            />
          </Col>
          <Col>
            <Form.Control
              placeholder="Име на кандидат"
              value={candidate.candidateName}
              onChange={(e) =>
                handleCandidateChange(index, "candidateName", e.target.value)
              }
            />
          </Col>
          {candidates.length > 1 && index !== candidates.length - 1 && (
            <Col className={styles.createVotingCampaignCandidateColumn}>
              <Button
                className={styles.createVotingCampaignCandidateDeleteButton}
                onClick={() => removeCandidate(index)}
              >
                изтрий
              </Button>
            </Col>
          )}
          {index === candidates.length - 1 && (
            <Col className={styles.createVotingCampaignCandidateColumn}>
              <Button
                disabled={addButtonDisabled}
                className={styles.createVotingCampaignCandidateAddButton}
                onClick={addCandidate}
              >
                добави
              </Button>
            </Col>
          )}
        </Row>
      ))}
    </InputGroup>
  );
}
