import styles from "./ListComponent.module.css";
import { useState } from "react";

export function ListComponent() {
  const [votesData, setVotesData] = useState([]);

  function handleClick(e) {
    e.preventDefault();

    fetch("http://localhost:8080/api/votes", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((data) => {
        setVotesData(data);
      })
      .catch((error) => console.error("Error:", error.message));
  }

  return (
    <div className={styles.container}>
      <h1 className={styles.formTitle}>Списък с гласове:</h1>

      <button className={styles.formButton} onClick={handleClick}>
        Генерирай
      </button>

      {votesData.length === 0 ? (
        ""
      ) : (
        <ul className={styles.listDataContainer}>
          {votesData.map(({ voterName, voteOption }, index) => {
            return (
              <li key={index}>
                {voterName} гласува за {voteOption}.
              </li>
            );
          })}
        </ul>
      )}

      {votesData.length === 0 ? "" : <p>Общо гласували: {votesData.length}</p>}
    </div>
  );
}
