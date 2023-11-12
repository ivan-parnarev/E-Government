import React from "react";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./VotingModalComponent.module.css";

export function PinInputComponent({ pinValue, isValidPinValue, onChange }) {
  let errorMessage = "";

  if (!isValidPinValue) {
    errorMessage = "Въведеното ЕГН трябва да съдържа само цифри.";
  } else if (pinValue.length < 10) {
    errorMessage = "Въведеното ЕГН съдържа по-малко от 10 цифри.";
  } else if (pinValue.length > 10) {
    errorMessage = "Въведеното ЕГН съдържа повече от 10 цифри.";
  }

  return (
    <>
      <h4>Влезли сте като гост</h4>
      <p>Моля въведете ЕГН, за да се идентифицирате:</p>
      <InputGroup size="sm" className="mb-3">
        <InputGroup.Text id="inputGroup-sizing-sm">ЕГН:</InputGroup.Text>
        <Form.Control
          aria-label="Small"
          aria-describedby="inputGroup-sizing-sm"
          value={pinValue}
          onChange={onChange}
        />
      </InputGroup>
      {errorMessage && pinValue.length > 0 && (
        <p className={styles.invalidInput}>{errorMessage}</p>
      )}
    </>
  );
}
