import styles from "./FormComponent.module.css";
import { useState } from "react";

function FormComponent() {
  const [voterName, setVoterName] = useState("");
  const [voteOption, setVoteOption] = useState("");

  const handleNameChange = (e) => setVoterName(e.target.value);

  const handleOptionChange = (e) => setVoteOption(e.target.value);

  function handleSubmit(e) {
    e.preventDefault();

    fetch("http://localhost:8080/api/votes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ voterName, voteOption }),
    })
      .then((response) => response.json())
      .then((data) => {
        const successMessage = `Congrats, ${data.voterName}, you've successfully voted for ${data.voteOption}`;
        alert(successMessage);
      })
      .catch((error) => console.error("Error:", error.message));
  }

  return (
    <div className={styles.container}>
      <h1 className={styles.formTitle}>Твоето име:</h1>
      <input
        type="name"
        id="voter_name"
        name="voter_name"
        value={voterName}
        onChange={handleNameChange}
      />

      <h1 className={styles.formTitle}>Гласувай:</h1>

      <form className={styles.formContainer} onSubmit={handleSubmit}>
        <div className={styles.formOptionsContainer}>
          <div>
            <input
              type="radio"
              id="bogo"
              name="vote"
              value="Бого"
              onChange={handleOptionChange}
            />
            <label className={styles.formOption} htmlFor="bogo">
              Бого
            </label>
          </div>
          <div>
            <input
              type="radio"
              id="yana"
              name="vote"
              value="Яна"
              onChange={handleOptionChange}
            />
            <label className={styles.formOption} htmlFor="yana">
              Яна
            </label>
          </div>
          <div>
            <input
              type="radio"
              id="koko"
              name="vote"
              value="Коко"
              onChange={handleOptionChange}
            />
            <label className={styles.formOption} htmlFor="koko">
              Коко
            </label>
          </div>
          <div>
            <input
              type="radio"
              id="nedko"
              name="vote"
              value="Недко"
              onChange={handleOptionChange}
            />
            <label className={styles.formOption} htmlFor="nedko">
              Недко
            </label>{" "}
          </div>
        </div>
        <button className={styles.formButton} type="submit">
          Изпрати
        </button>
      </form>
    </div>
  );
}

export default FormComponent;
