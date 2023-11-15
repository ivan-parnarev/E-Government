import React from "react";
import Form from "react-bootstrap/Form";
import styles from "./ElectionRowComponent.module.css";

interface ElectionRowProps {
  candidateId: string;
  candidateName: string;
  candidateParty: string;
  candidateNumber: string;
  checked: boolean;
  onChange: (id: string, name: string, number: string) => void;
}

function ElectionRowComponent({
  candidateId,
  candidateName,
  candidateParty,
  candidateNumber,
  checked,
  onChange,
}: ElectionRowProps) {
  return (
    <Form className={styles.electionRowContainer}>
      <div key={candidateId} className="mb-3">
        <Form.Check
          type="checkbox"
          id={`check-${candidateId}`}
          className={styles.electionRowCheck}
        >
          <Form.Check.Input
            type="checkbox"
            checked={checked}
            onChange={() =>
              onChange(candidateId, candidateParty, candidateNumber)
            }
            className={styles.electionRowCheckbox}
          />{" "}
          <Form.Check.Label className={styles.electionRowCheckLabel}>
            {candidateNumber}. {candidateParty}
          </Form.Check.Label>
        </Form.Check>
      </div>
    </Form>
  );
}

export default ElectionRowComponent;
