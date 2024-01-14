export interface VotingModalProps {
  show: boolean;
  onHide: () => void;
  campaignTitle: string;
  campaignDescription: string;
  electionId: string;
  electionCandidates: {
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }[];
}

export interface UserData {
  userPin: string;
  electionId?: string;
  candidate?: { id: string; name: string; number: string };
}
