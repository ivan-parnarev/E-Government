import React from "react";
import Form from "react-bootstrap/Form";
import styles from "./ElectionRowComponent.module.css";

interface ElectionRowProps {
  id: string;
  name: string;
  number: string;
  checked: boolean;
  onChange: (id: string, name: string, number: string) => void;
}

function ElectionRowComponent({
  id,
  name,
  number,
  checked,
  onChange,
}: ElectionRowProps) {
  return (
    <Form className={styles.electionRowContainer}>
      <div key={id} className="mb-3">
        <Form.Check
          type="checkbox"
          id={`check-${id}`}
          className={styles.electionRowCheck}
        >
          <Form.Check.Input
            type="checkbox"
            checked={checked}
            onChange={() => onChange(id, name, number)}
            className={styles.electionRowCheckbox}
          />{" "}
          <Form.Check.Label className={styles.electionRowCheckLabel}>
            {number}. {name}
          </Form.Check.Label>
        </Form.Check>
      </div>
    </Form>
  );
}

export default ElectionRowComponent;
