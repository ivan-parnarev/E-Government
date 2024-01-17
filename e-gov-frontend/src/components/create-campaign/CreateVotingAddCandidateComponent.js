import axios from "axios";
import API_URLS from "../../utils/apiUtils";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./CreateVotingAddCandidateComponent.module.css";
import { useEffect, useState } from "react";

export function CreateVotingAddCandidateComponent({
  electionType,
  candidates,
  setCandidates,
  regions,
}) {
  const [addButtonDisabled, setAddButtonDisabled] = useState(true);
  const [activeRegion, setActiveRegion] = useState("");
  const [modifyingIndex, setModifyingIndex] = useState(null);

  const initializeCandidates = () => {
    if (!candidates[activeRegion]) {
      setCandidates({
        ...candidates,
        [activeRegion]: [createEmptyCandidate()],
      });
    }
  };

  useEffect(() => {
    initializeCandidates();
  }, [activeRegion, candidates]);

  const createEmptyCandidate = () => ({
    candidateName: "",
    candidateParty: "",
    candidateNumber: "",
  });

  const handleRegionClick = (region) => {
    setActiveRegion(region);

    if (!candidates[region]) {
      setCandidates({
        ...candidates,
        [region]: [createEmptyCandidate()],
      });
    }

    setModifyingIndex(null);
    setAddButtonDisabled(true);
  };

  const handleCandidateChange = (field, value, index) => {
    const newCandidates = [...candidates[activeRegion]];
    newCandidates[index][field] = value;

    setCandidates({ ...candidates, [activeRegion]: [...newCandidates] });

    const isLastCandidateEmpty =
      newCandidates[index].candidateParty === "" ||
      newCandidates[index].candidateNumber === "" ||
      newCandidates[index].candidateName === "";

    setAddButtonDisabled(isLastCandidateEmpty);
  };

  const startModifyCandidate = (index) => {
    setModifyingIndex(index);
  };

  const saveModifiedCandidate = () => {
    setModifyingIndex(null);
    setAddButtonDisabled(true);
  };

  const addCandidate = () => {
    setAddButtonDisabled(true);

    const newCandidate = createEmptyCandidate();

    const existingRegionCandidates = candidates[activeRegion] || [];

    setCandidates({
      ...candidates,
      [activeRegion]: [...existingRegionCandidates, newCandidate],
    });
  };

  const removeCandidate = (index) => {
    const newCandidates = [...candidates[activeRegion]];
    if (modifyingIndex === index) {
      saveModifiedCandidate();
    } else {
      newCandidates.splice(index, 1);
      setCandidates({
        ...candidates,
        [activeRegion]: newCandidates,
      });
    }
  };

  const chunkArray = (arr, chunkSize) => {
    const result = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
      result.push(arr.slice(i, i + chunkSize));
    }
    return result;
  };

  const groupedRegions = chunkArray(regions, 7);

  return (
    <div className={styles.createVotingCampaignContainer}>
      {electionType === "MAYOR" &&
        groupedRegions.map((row, rowIndex) => (
          <Row key={rowIndex} className="mb-2">
            <ButtonGroup aria-label="Basic example">
              {row
                .filter((region) => region.id !== 1)
                .map((region) => (
                  <Button
                    key={region.id}
                    variant={
                      activeRegion === region.englishRegionName
                        ? "primary"
                        : candidates[region.englishRegionName] &&
                          candidates[region.englishRegionName].length > 1
                        ? "success"
                        : "secondary"
                    }
                    onClick={() => handleRegionClick(region.englishRegionName)}
                  >
                    {region.bulgarianRegionName}
                  </Button>
                ))}
            </ButtonGroup>
          </Row>
        ))}

      <InputGroup className={styles.createVotingCampaignInputGroup}>
        <p className={styles.createVotingCampaignInputGroupInputLabel}>
          Кандидати
          {electionType === "MAYOR" &&
            ` за ${
              regions.find(
                (region) => region.englishRegionName === activeRegion
              )?.bulgarianRegionName
            }`}
          :
        </p>
        {(candidates[activeRegion] || []).map((candidate, index) => (
          <Row key={index} className={styles.createVotingCampaignCandidate}>
            <Col>
              <Form.Control
                placeholder="Име на партия"
                value={candidate.candidateParty}
                onChange={(e) =>
                  handleCandidateChange("candidateParty", e.target.value, index)
                }
              />
            </Col>
            <Col>
              <Form.Control
                placeholder="Номер на кандидат"
                value={candidate.candidateNumber}
                onChange={(e) =>
                  handleCandidateChange(
                    "candidateNumber",
                    e.target.value,
                    index
                  )
                }
              />
            </Col>
            <Col>
              <Form.Control
                placeholder="Име на кандидат"
                value={candidate.candidateName}
                onChange={(e) =>
                  handleCandidateChange("candidateName", e.target.value, index)
                }
              />
            </Col>
            {candidates[activeRegion].length > 1 &&
              index !== candidates[activeRegion].length - 1 && (
                <Col className={styles.createVotingCampaignCandidateColumn}>
                  <Button
                    className={styles.createVotingCampaignCandidateModifyButton}
                    onClick={() => startModifyCandidate(index)}
                  >
                    промени
                  </Button>
                  <Button
                    className={styles.createVotingCampaignCandidateDeleteButton}
                    onClick={() => removeCandidate(index)}
                  >
                    изтрий
                  </Button>
                </Col>
              )}
            {index === candidates[activeRegion].length - 1 && (
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
    </div>
  );
}
