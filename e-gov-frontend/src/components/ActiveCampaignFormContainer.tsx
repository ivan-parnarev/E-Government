import ElectionRowComponent from "./ElectionRowComponent";
import styles from "./ActiveCampaignFormContainer.module.css";
import CensusModalComponent from "./CensusModalComponent.tsx";

interface ActiveCampaignFormContainerProps {
  campaignType: string;
  answersJson: Array<{
    id: string;
    name: string;
    number: string;
  }>;
  checkedId: string | null;
  handleCheckboxChange: (id: string, name: string, number: string) => void;
}

function ActiveCampaignFormContainer({
  campaignType,
  answersJson,
  checkedId,
  handleCheckboxChange,
}: ActiveCampaignFormContainerProps) {
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
