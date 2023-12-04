import styles from "./ActiveCampaignsPage.module.css";
import { ActiveCampaignsContainerComponent } from "../components/ActiveCampaignsContainerComponent";
import { CreateCampaignContainerComponent } from "../components/create-campaign/CreateCampaignContainerComponent";

export function ActiveCampaignsPage() {
  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        <ActiveCampaignsContainerComponent />
        <CreateCampaignContainerComponent />
      </div>
    </div>
  );
}
