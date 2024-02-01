export interface CommonCampaignProps {
  campaignType: string;
  campaignTitle: string;
  campaignDescription: string;
}

export interface VotingCampaignProps extends CommonCampaignProps {
  electionId: string;
  electionCandidates: {
    candidateId: string;
    candidateName: string;
    candidateParty: string;
    candidateNumber: string;
  }[];
}

export interface CensusCampaignProps extends CommonCampaignProps {
  censusId: number;
  campaignId: string;
  censusQuestions: {
    text: string;
    answers: {
      answerText: string;
    }[];
    questionCategory: string;
  }[];
}

export type CampaignProps = VotingCampaignProps | CensusCampaignProps;
