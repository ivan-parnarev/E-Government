import styles from "./CreateVotingReviewCandidatesComponent.module.css";
import Accordion from "react-bootstrap/Accordion";

export function CreateVotingReviewCandidatesComponent({
  campaignData,
  electionType,
  regions,
}) {
  const { candidates } = campaignData;

  const filteredCandidates = Object.entries(candidates)
    .filter(([region, regionCandidates]) =>
      regionCandidates.some(
        (candidate) =>
          candidate.candidateParty?.trim() !== "" ||
          candidate.candidateNumber?.trim() !== "" ||
          candidate.candidateName?.trim() !== ""
      )
    )
    .reduce((obj, [region, candidates]) => {
      obj[region] = candidates;
      return obj;
    }, {});

  return (
    <div className={styles.createVotingCampaignReviewCandidatesContainer}>
      {electionType === "MAYOR" || electionType === "COUNCIL" ? (
        <Accordion defaultActiveKey="0">
          {Object.entries(filteredCandidates).map(
            ([region, regionCandidates], index) =>
              region &&
              regionCandidates && (
                <Accordion.Item key={region} eventKey={index}>
                  <Accordion.Header>
                    {
                      regions.find((r) => r.englishRegionName === region)
                        ?.bulgarianRegionName
                    }
                  </Accordion.Header>
                  <Accordion.Body>
                    <ul>
                      {regionCandidates.map((candidate, candidateIndex) => {
                        const isCandidateEmpty =
                          candidate.candidateParty === "" ||
                          candidate.candidateNumber === "" ||
                          candidate.candidateName === "";

                        return isCandidateEmpty ? null : (
                          <li key={candidateIndex}>
                            <strong>Име на кандидат:</strong>{" "}
                            {candidate.candidateName}, &nbsp;
                            <strong>Име на партия:</strong>{" "}
                            {candidate.candidateParty}, &nbsp;
                            <strong>Номер на кандидат:</strong>{" "}
                            {candidate.candidateNumber}
                          </li>
                        );
                      })}
                    </ul>
                  </Accordion.Body>
                </Accordion.Item>
              )
          )}
        </Accordion>
      ) : (
        <ul>
          {Object.values(filteredCandidates).map((regionCandidates, index) =>
            regionCandidates.map((candidate, candidateIndex) => {
              const isCandidateEmpty =
                candidate.candidateParty === "" ||
                candidate.candidateNumber === "" ||
                candidate.candidateName === "";

              return isCandidateEmpty ? null : (
                <li key={`${index}-${candidateIndex}`}>
                  <strong>Име на кандидат:</strong> {candidate.candidateName},
                  &nbsp;
                  <strong>Име на партия:</strong> {candidate.candidateParty},
                  &nbsp;
                  <strong>Номер на кандидат:</strong>{" "}
                  {candidate.candidateNumber}
                </li>
              );
            })
          )}
        </ul>
      )}
    </div>
  );
}
