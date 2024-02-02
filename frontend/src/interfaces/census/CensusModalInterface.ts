export interface CensusModalProps {
  show: boolean;
  onHide: () => void;
  campaignTitle: string;
  campaignDescription: string;
  censusId: string;
}

export interface UserData {
  userPin: string;
  campaignId: string;
  censusAnswers: Array<{
    questionText: string;
    answer: string;
    questionCategory: string;
  }>;
}
