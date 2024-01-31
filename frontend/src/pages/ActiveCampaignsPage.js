import styles from "./ActiveCampaignsPage.module.css";
import { ActiveCampaignsContainerComponent } from "../components/ActiveCampaignsContainerComponent";

export function ActiveCampaignsPage() {
  return (
    <div className={styles.container}>
      <ActiveCampaignsContainerComponent />
    </div>
  );
}
