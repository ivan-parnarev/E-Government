import styles from "./CreateVotingReviewCandidatesComponent.module.css";
import Accordion from "react-bootstrap/Accordion";

export function CreateVotingReviewCandidatesComponent({ campaignData }) {
  const { candidates } = campaignData;

  return (
    <Accordion
      defaultActiveKey="0"
      className={styles.createVotingCampaignReviewCandidatesContainer}
    >
      {Object.entries(candidates).map(([region, regionCandidates], index) => (
        <Accordion.Item key={region} eventKey={index}>
          <Accordion.Header>{region}</Accordion.Header>
          <Accordion.Body>
            <ul>
              {regionCandidates.map((candidate, candidateIndex) => {
                const isCandidateEmpty =
                  candidate.candidateParty === "" ||
                  candidate.candidateNumber === "" ||
                  candidate.candidateName === "";

                return isCandidateEmpty ? null : (
                  <li key={candidateIndex}>
                    <strong>Име на кандидат:</strong> {candidate.candidateName},
                    &nbsp;
                    <strong>Име на партия:</strong> {candidate.candidateParty},
                    &nbsp;
                    <strong>Номер на кандидат:</strong>{" "}
                    {candidate.candidateNumber}
                  </li>
                );
              })}
            </ul>
          </Accordion.Body>
        </Accordion.Item>
      ))}
    </Accordion>
  );
}
