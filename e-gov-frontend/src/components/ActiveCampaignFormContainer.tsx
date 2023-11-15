import ElectionRowComponent from "./ElectionRowComponent";
import styles from "./ActiveCampaignFormContainer.module.css";
import CensusModalComponent from "./CensusModalComponent.tsx";

interface ActiveCampaignFormContainerProps {
  campaignType: string;
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

function ActiveCampaignFormContainer({
  campaignType,
  campaignDescription,
  electionCandidates,
  checkedId,
  handleCheckboxChange,
}: ActiveCampaignFormContainerProps) {
  return (
    <>
      <h5>{campaignDescription}</h5>
      {campaignType === "VOTING" ? (
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
      ) : (
        ""
      )}

      {campaignType === "CENSUS" ? (
        <CensusModalComponent
          censusCategories={Object.keys(electionCandidates)}
          censusData={Object.entries(electionCandidates)}
        />
      ) : (
        ""
      )}
    </>
  );
}

export default ActiveCampaignFormContainer;
