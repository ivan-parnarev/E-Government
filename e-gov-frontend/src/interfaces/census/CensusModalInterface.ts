export interface CensusModalProps {
  show: boolean;
  onHide: () => void;
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

export interface UserData {
  userPin: string;
  campaignId: string;
  censusAnswers: Array<{
    questionText: string;
    answerText: string;
    questionCategory: string;
  }>;
}
