import React from "react";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./VotingModalComponent.module.css";

export function PinInputComponent({ pinValue, onChange, onFocus, isFocused }) {
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
          onFocus={onFocus}
        />
      </InputGroup>
      {pinValue.length < 10 && isFocused ? (
        <p className={styles.invalidInput}>
          Въведеното ЕГН съдържа по-малко от 10 цифри.
        </p>
      ) : (
        ""
      )}
      {pinValue.length > 10 ? (
        <p className={styles.invalidInput}>
          Въведеното ЕГН съдържа по-повече от 10 цифри.
        </p>
      ) : (
        ""
      )}
    </>
  );
}
