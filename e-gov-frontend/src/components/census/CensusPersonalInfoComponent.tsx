import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import { ChangeEvent } from "react";
import styles from "./CensusPersonalInfo.module.css";

interface CensusPersonalInfoComponentProps {
  censusQuestions: Array<{ text: string; questionCategory: string }>;
  onContinue: () => void;
  onInputChange: (text: string, value: string) => void;
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
      {censusQuestions.map(({ text, questionCategory }) => (
        <InputGroup className={styles.inputGroup} key={text}>
          <p className={styles.inputGroupInputLabel}>{text}:</p>
          <Form.Control
            placeholder=""
            aria-label=""
            aria-describedby="basic-addon1"
            className={styles.inputGroupInputField}
            onChange={(event) => onInputChange(text, event.target.value)}
          />
        </InputGroup>
      ))}
    </div>
  );
}

export default CensusPersonalInfoComponent;
