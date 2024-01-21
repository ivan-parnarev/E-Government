import React from "react";
import styles from "./HomePage.module.css";
import { ActiveCampaignsContainerComponent } from "../components/ActiveCampaignsContainerComponent";

export function HomePage() {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Министерство на електронното управление</h1>
      <h2 className={styles.subTitle}>
        Иновативна система за електронно преброяване и гласуване
      </h2>

      <ActiveCampaignsContainerComponent />
    </div>
  );
}
