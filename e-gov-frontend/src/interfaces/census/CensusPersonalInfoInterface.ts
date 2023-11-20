interface CensusPersonalInfoProps {
  censusQuestions: Array<{
    id: string;
    text: string;
    questionCategory: string;
  }>;
  onContinue: () => void;
  onInputChange: (questionId: string, text: string, value: string) => void;
}
