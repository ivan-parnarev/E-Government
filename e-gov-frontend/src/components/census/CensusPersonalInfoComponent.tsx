import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import { ChangeEvent } from "react";
import styles from "./CensusPersonalInfo.module.css";

interface CensusPersonalInfoComponentProps {
  censusQuestions: Array<{
    id: string;
    text: string;
    questionCategory: string;
  }>;
  onContinue: () => void;
  onInputChange: (questionId: string, text: string, value: string) => void;
}

function CensusPersonalInfoComponent({
  censusQuestions,
  onInputChange,
}: CensusPersonalInfoComponentProps) {
  return (
    <div>
      <h3 className={styles.inputGroupTitle}>
        <b>Лична информация</b>
      </h3>
      {censusQuestions.map(({ id, text, questionCategory }) => (
        <InputGroup className={styles.inputGroup} key={id}>
          <p className={styles.inputGroupInputLabel}>{text}:</p>
          <Form.Control
            placeholder=""
            aria-label=""
            aria-describedby="basic-addon1"
            className={styles.inputGroupInputField}
            onChange={(event) => onInputChange(id, text, event.target.value)}
          />
        </InputGroup>
      ))}
    </div>
  );
}

export default CensusPersonalInfoComponent;
