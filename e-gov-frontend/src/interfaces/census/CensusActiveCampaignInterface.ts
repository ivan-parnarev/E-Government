export interface CensusActiveCampaignProps {
  campaignTitle: string;
  campaignDescription: string;
  censusId: string;
  censusQuestions: {
    id: string;
    text: string;
    questionCategory: string;
  }[];
}
