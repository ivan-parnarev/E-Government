import { CommonCampaignProps } from "../ActiveCampaignsContainerInterface";

export interface VotingActiveCampaignProps {
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
