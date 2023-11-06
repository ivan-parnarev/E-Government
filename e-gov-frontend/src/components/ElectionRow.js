import React from "react";
import Form from "react-bootstrap/Form";
import styles from "./ElectionRow.module.css";

export function ElectionRow() {
  return (
    <Form className={styles.electionRowContainer}>
      {["checkbox", "checkbox", "checkbox"].map((type) => (
        <div key={type} className="mb-3">
          <Form.Check
            type={type}
            id={`check-api-${type}`}
            className={styles.electionRowCheck}
          >
            <Form.Check.Label className={styles.electionRowCheckLabel}>
              Политическа партия Х
            </Form.Check.Label>{" "}
            <Form.Check.Input
              type={type}
              className={styles.electionRowCheckbox}
            />{" "}
            <Form.Check.Label className={styles.electionRowCheckLabel}>
              Иван Иванов Иванов
            </Form.Check.Label>
          </Form.Check>
        </div>
      ))}
    </Form>
  );
}
