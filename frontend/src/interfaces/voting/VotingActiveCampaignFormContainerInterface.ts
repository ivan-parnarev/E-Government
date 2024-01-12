export interface VotingActiveCampaignFormContainerProps {
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
