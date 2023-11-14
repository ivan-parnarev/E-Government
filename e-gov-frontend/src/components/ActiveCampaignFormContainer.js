import ElectionRowComponent from "./ElectionRowComponent";
import styles from "./ActiveCampaignFormContainer.module.css";
import CensusModalComponent from "./CensusModalComponent";

function ActiveCampaignFormContainer({
  campaignType,
  answersJson,
  checkedId,
  handleCheckboxChange,
}) {
  return (
    <>
      {/* <h5>БЮЛЕТИНА ЗА НАРОДНИ ПРЕДСТАВИТЕЛИ</h5> */}
      {campaignType === "VOTING" ? (
        <div className={styles.electionRowContainerPosition}>
          <div className={styles.electionRowContainer}>
            {answersJson.map((answer) => {
              return (
                <ElectionRowComponent
                  key={answer.id}
                  id={answer.id}
                  name={answer.name}
                  number={answer.number}
                  checked={checkedId === answer.id}
                  onChange={handleCheckboxChange}
                />
              );
            })}
          </div>
        </div>
      ) : (
        ""
      )}

      {campaignType === "CENSUS" ? (
        <CensusModalComponent
          censusCategories={Object.keys(answersJson)}
          censusData={Object.entries(answersJson)}
        />
      ) : (
        ""
      )}
    </>
  );
}

export default ActiveCampaignFormContainer;
