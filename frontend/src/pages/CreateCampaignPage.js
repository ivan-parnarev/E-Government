import styles from "./CreateCampaignPage.module.css";
import { CreateCampaignContainerComponent } from "../components/create-campaign/CreateCampaignContainerComponent";

export function CreateCampaignPage() {
  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        <CreateCampaignContainerComponent />
      </div>
    </div>
  );
}
