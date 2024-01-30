export interface CensusActiveCampaignProps {
  campaignTitle: string;
  campaignDescription: string;
  censusId: string;
  censusQuestions: {
    text: string;
    answers: Array<{
      answerText: string;
    }>;
    questionCategory: string;
  }[];
}
