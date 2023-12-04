import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import styles from "./UserGuestComponent.module.css";
import { UserGuestProps } from "../../interfaces/UserGuestInterface.ts";

function UserGuestComponent({
  pinValue,
  isValidPinValue,
  onChange,
}: UserGuestProps) {
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
      <h4>Продължете като гост</h4>
      <p>Моля въведете ЕГН, за да се идентифицирате:</p>
      <FloatingLabel controlId="floatingInput" label="ЕГН:" className="mb-3">
        <Form.Control
          type="text"
          placeholder=""
          value={pinValue}
          onChange={onChange}
        />
      </FloatingLabel>

      {errorMessage && pinValue.length > 0 && (
        <p className={styles.invalidInput}>{errorMessage}</p>
      )}
    </div>
  );
}

export default UserGuestComponent;
