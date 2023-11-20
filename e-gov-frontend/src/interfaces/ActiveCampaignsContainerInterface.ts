export interface CommonCampaignProps {
  campaignType: string;
  campaignTitle: string;
  campaignDescription: string;
}

export interface VoteCampaignProps extends CommonCampaignProps {
  electionId: string;
  electionCandidates: {
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }[];
}

export interface CensusCampaignProps extends CommonCampaignProps {
  id: string;
  censusQuestions: {
    id: string;
    text: string;
    questionCategory: string;
  }[];
}
