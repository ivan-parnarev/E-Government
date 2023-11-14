import React from "react";
import Form from "react-bootstrap/Form";
import styles from "./CensusRowComponent.module.css";

function CensusRowComponent({ id, name, number, checked, onChange }) {
  return (
    <Form className={styles.censusRowContainer}>
      <div key={id} className="mb-3">
        <Form.Check
          type="checkbox"
          id={`check-${id}`}
          className={styles.censusRowCheck}
        >
          <Form.Check.Input
            type="checkbox"
            checked={checked}
            onChange={() => onChange(id, name, number)}
            className={styles.censusRowCheckbox}
          />{" "}
          <Form.Check.Label className={styles.censusRowCheckLabel}>
            {number}. {name}
          </Form.Check.Label>
        </Form.Check>
      </div>
    </Form>
  );
}

export default CensusRowComponent;
