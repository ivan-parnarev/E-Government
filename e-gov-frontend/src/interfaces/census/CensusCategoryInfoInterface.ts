interface CensusCategoryInfoProps {
  censusTitle: string;
  censusQuestions: Array<{
    text: string;
    answers: Array<{
      answerText: string;
    }>;
    questionCategory: string;
  }>;
  onInputChange: (
    text: string,
    value: string,
    questionCategory: string
  ) => void;
}
