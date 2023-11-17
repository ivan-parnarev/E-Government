import ElectionRowComponent from "./ElectionRowComponent.tsx";
import styles from "./VotingActiveCampaignFormContainer.module.css";
// import CensusModalComponent from "./CensusModalComponent.tsx";

interface VotingActiveCampaignFormContainerProps {
  campaignDescription: string;
  electionCandidates: Array<{
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }>;
  checkedId: string | null;
  handleCheckboxChange: (candidateId: string) => void;
}

function VotingActiveCampaignFormContainer({
  campaignDescription,
  electionCandidates,
  checkedId,
  handleCheckboxChange,
}: VotingActiveCampaignFormContainerProps) {
  return (
    <>
      <h5>{campaignDescription}</h5>

      <div className={styles.electionRowContainerPosition}>
        <div className={styles.electionRowContainer}>
          {electionCandidates.map((candidate) => {
            return (
              <ElectionRowComponent
                key={candidate.candidateId}
                candidateId={candidate.candidateId}
                candidateName={candidate.candidateName}
                candidateParty={candidate.candidateParty}
                candidateNumber={candidate.candidateNumber}
                checked={checkedId === candidate.candidateId}
                onChange={handleCheckboxChange}
              />
            );
          })}
        </div>
      </div>
    </>
  );
}

export default VotingActiveCampaignFormContainer;
