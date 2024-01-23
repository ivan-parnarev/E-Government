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
  campaignId: string;
  censusQuestions: {
    text: string;
    answers: Array<{
      answerText: string;
    }>;
    questionCategory: string;
  }[];
}
