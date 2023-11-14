function CensusDemographicInfoComponent({ censusQuestions, onInputChange }) {
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
