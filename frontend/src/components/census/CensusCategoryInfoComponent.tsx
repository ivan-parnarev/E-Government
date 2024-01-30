import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./CensusCategoryInfoComponent.module.css";

function CensusCategoryInfoComponent({
  censusTitle,
  censusQuestions,
  onInputChange,
}: CensusCategoryInfoProps) {
  return (
    <div className={styles.censusCategoryInfoContainer}>
      <h3 className={styles.inputGroupTitle}>
        <b>{censusTitle}</b>
      </h3>
      {censusQuestions.map(({ text, answers, questionCategory }) => (
        <InputGroup className={styles.inputGroup} key={text}>
          <p className={styles.inputGroupInputLabel}>
            <b>{text}</b>
          </p>
          <Form>
            {answers.map((answer) => {
              return (
                <Form.Check
                  key={answer.answerText}
                  type="radio"
                  id={text}
                  name={text}
                  label={answer.answerText}
                  onChange={() =>
                    onInputChange(text, answer.answerText, questionCategory)
                  }
                />
              );
            })}
          </Form>
        </InputGroup>
      ))}
    </div>
  );
}

export default CensusCategoryInfoComponent;
