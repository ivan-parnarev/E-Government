import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import styles from "./UserGuestComponent.module.css";
import usePinInput from "../../hooks/usePinInput.js";
import useAuth from "../../hooks/AuthContext.js";

function UserGuestComponent() {
  const { login } = useAuth();
  const { pinValue, isValidPinValue, handlePinChange } = usePinInput();
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
      <h4>Влезте в профила си</h4>
      <p>Моля въведете ЕГН, за да се идентифицирате:</p>
      <FloatingLabel controlId="floatingInput" label="ЕГН:" className="mb-3">
        <Form.Control
          className={styles.inputGroupInputField}
          type="text"
          placeholder=""
          value={pinValue}
          onChange={handlePinChange}
        />
      </FloatingLabel>

      {errorMessage && pinValue.length > 0 && (
        <p className={styles.invalidInput}>{errorMessage}</p>
      )}

      <div className={styles.loginButtonContainer}>
        <Button disabled={!pinValue} onClick={() => login(pinValue)}>
          Влез
        </Button>
      </div>
    </div>
  );
}

export default UserGuestComponent;
