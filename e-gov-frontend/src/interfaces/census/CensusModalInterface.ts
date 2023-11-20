export interface CensusModalProps {
  show: boolean;
  onHide: () => void;
  campaignTitle: string;
  campaignDescription: string;
  censusId: string;
  censusQuestions: {
    id: string;
    text: string;
    questionCategory: string;
  }[];
}

export interface UserData {
  userPin: string;
  campaignId: string;
  censusAnswers: Array<{
    questionId: string;
    text: string;
    answer: string;
    questionCategory: string;
  }>;
}
