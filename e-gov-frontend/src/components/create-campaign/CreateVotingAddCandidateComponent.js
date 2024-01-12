import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./CreateVotingAddCandidateComponent.module.css";
import { useEffect, useState } from "react";

const regions = [
  "Благоевград",
  "Добрич",
  "Плевен",
  "София",
  "Бургас",
  "Кърджали",
  "Пловдив",
  "София (столица)",
  "Варна",
  "Кюстендил",
  "Разград",
  "Стара Загора",
  "Велико Търново",
  "Ловеч",
  "Русе",
  "Търговище",
  "Видин",
  "Монтана",
  "Силистра",
  "Хасково",
  "Враца",
  "Пазарджик",
  "Сливен",
  "Шумен",
  "Габрово",
  "Перник",
  "Смолян",
  "Ямбол",
];

export function CreateVotingAddCandidateComponent({
  electionType,
  candidates,
  setCandidates,
}) {
  const [addButtonDisabled, setAddButtonDisabled] = useState(true);
  const [activeRegion, setActiveRegion] = useState("");
  const [modifyingIndex, setModifyingIndex] = useState(null);

  useEffect(() => {
    const initialRegion = regions[0];
    setActiveRegion(initialRegion);

    if (!candidates[initialRegion]) {
      setCandidates({
        ...candidates,
        [initialRegion]: [createEmptyCandidate()],
      });
    }
  }, []);

  const createEmptyCandidate = () => ({
    candidateName: "",
    candidateParty: "",
    candidateNumber: "",
  });

  const handleRegionClick = (region) => {
    setActiveRegion(region);
    const newCandidates = candidates[region] || [];

    setCandidates({
      ...candidates,
      [region]:
        newCandidates.length > 0 ? newCandidates : [createEmptyCandidate()],
    });

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
              {row.map((region) => (
                <Button
                  key={region}
                  variant={
                    activeRegion === region
                      ? "primary"
                      : candidates[region] && candidates[region].length > 1
                      ? "success"
                      : "secondary"
                  }
                  onClick={() => handleRegionClick(region)}
                >
                  {region}
                </Button>
              ))}
            </ButtonGroup>
          </Row>
        ))}

      <InputGroup className={styles.createVotingCampaignInputGroup}>
        <p className={styles.createVotingCampaignInputGroupInputLabel}>
          Кандидати за {activeRegion}:
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
