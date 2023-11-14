interface CensusEducationInfoComponentProps {
  censusQuestions: Array<{ _: any; question: string }>;
  onContinue: () => void;
  onInputChange: (fieldName: string, value: any) => void;
}

function CensusEducationInfoComponent({
  censusQuestions,
  onInputChange,
}: CensusEducationInfoComponentProps) {
  return (
    <>
      <h3>Образование</h3>
      {censusQuestions.map(({ _, question }) => {
        return (
          <div key={question}>
            <p>{question}</p>
          </div>
        );
      })}
    </>
  );
}

export default CensusEducationInfoComponent;
