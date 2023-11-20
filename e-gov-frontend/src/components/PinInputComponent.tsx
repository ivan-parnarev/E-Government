import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./PinInputComponent.module.css";
import { PinInputProps } from "../interfaces/PinInputInterface";

function PinInputComponent({
  pinValue,
  isValidPinValue,
  onChange,
}: PinInputProps) {
  const [errorMessage, setErrorMessage] = useState<string | null>("");
  const timeoutDuration = 2000;

  useEffect(() => {
    let timeoutId: NodeJS.Timeout | undefined;

    setErrorMessage(null);

    if (!isValidPinValue) {
      timeoutId = setTimeout(() => {
        setErrorMessage("Въведеното ЕГН трябва да съдържа само цифри.");
      }, timeoutDuration);
    } else if (pinValue.length < 10) {
      timeoutId = setTimeout(() => {
        setErrorMessage("Въведеното ЕГН съдържа по-малко от 10 цифри.");
      }, timeoutDuration);
    } else if (pinValue.length > 10) {
      timeoutId = setTimeout(() => {
        setErrorMessage("Въведеното ЕГН съдържа повече от 10 цифри.");
      }, timeoutDuration);
    }

    return () => {
      clearTimeout(timeoutId);
    };
  }, [pinValue, isValidPinValue]);

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
