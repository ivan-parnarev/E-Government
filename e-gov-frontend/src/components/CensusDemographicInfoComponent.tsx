interface CensusDemographicInfoComponentProps {
  censusQuestions: Array<{ _: any; question: string }>;
  onContinue: () => void;
  onInputChange: (fieldName: string, value: any) => void;
}

function CensusDemographicInfoComponent({
  censusQuestions,
  onInputChange,
}: CensusDemographicInfoComponentProps) {
  return (
    <>
      <h3>Лична информация</h3>
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

export default CensusDemographicInfoComponent;
