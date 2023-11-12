import React from "react";
import styles from "./HomePage.module.css";
import { ActiveCampaignsContainerComponent } from "../components/ActiveCampaignsContainerComponent";

export function HomePage() {
  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        <h1 className={styles.title}>
          Министерство на електронното управление
        </h1>
        <h2 className={styles.subTitle}>
          Иновативна система за електронно преброяване и гласуване
        </h2>

        <div className={styles.featuresSection}>
          <h2 className={styles.subTitle}>Основни функционалности:</h2>
          <ul className={styles.featuresList}>
            <li>Лесно създаване на потребителски профили</li>
            <li>Удобно онлайн гласуване за актуални кампании и референдуми</li>
            <li>Стриктна идентификация и сигурност на гласовете</li>
            <li>
              Бърз и лесен достъп до резултатите и архива от предишни избори
            </li>
          </ul>
        </div>

        <div className={styles.aboutSection}>
          <h2 className={styles.subTitle}>За нас:</h2>
          <p className={styles.aboutSectionDescription}>
            Ние вярваме в силата на технологиите за улесняване на демократичните
            процеси. Системата ни за електронно преброяване и гласуване е
            създадена с оглед на повишаване на участието на гражданите във
            важните решения, които засягат бъдещето на нашето общество.
          </p>
        </div>
        <ActiveCampaignsContainerComponent />
      </div>
    </div>
  );
}
