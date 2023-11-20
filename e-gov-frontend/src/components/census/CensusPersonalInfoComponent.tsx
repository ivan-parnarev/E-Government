import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./CensusPersonalInfo.module.css";

function CensusPersonalInfoComponent({
  censusQuestions,
  onInputChange,
}: CensusPersonalInfoProps) {
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
