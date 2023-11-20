import React, { ChangeEvent } from "react";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./PinInputComponent.module.css";

interface PinInputProps {
  pinValue: string;
  isValidPinValue: boolean;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}

function PinInputComponent({
  pinValue,
  isValidPinValue,
  onChange,
}: PinInputProps) {
  let errorMessage = "";

  if (!isValidPinValue) {
    errorMessage = "Въведеното ЕГН трябва да съдържа само цифри.";
  } else if (pinValue.length < 10) {
    errorMessage = "Въведеното ЕГН съдържа по-малко от 10 цифри.";
  } else if (pinValue.length > 10) {
    errorMessage = "Въведеното ЕГН съдържа повече от 10 цифри.";
  }

  return (
    <div>
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
    </div>
  );
}

export default PinInputComponent;
