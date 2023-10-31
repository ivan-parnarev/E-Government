import styles from "./Vote.module.css";

function Vote() {
  return (
    <div className={styles.container}>
      <h1 className={styles.votesTitle}>VOTE DOWN BELOW:</h1>

      <div className={styles.votesContainer}>
        <div>
          <input type="radio" id="bogo" name="vote" value="Bogo" />
          <label for="bogo">Bogo</label>
        </div>
        <div>
          <input type="radio" id="yana" name="vote" value="Yana" />
          <label for="yana">Yana</label>
        </div>
        <div>
          <input type="radio" id="koko" name="vote" value="Koko" />
          <label for="koko">Koko</label>
        </div>
        <div>
          <input type="radio" id="nedko" name="vote" value="Nedko" />
          <label for="nedko">Nedko</label>{" "}
        </div>
      </div>
    </div>
  );
}

export default Vote;
