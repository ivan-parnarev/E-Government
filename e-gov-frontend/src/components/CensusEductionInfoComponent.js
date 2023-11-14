function CensusEducationInfoComponent({ censusQuestions, onInputChange }) {
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
