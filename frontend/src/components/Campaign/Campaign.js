import styles from './Campaign.module.css';

export default function Campaign({campaignTitle, regionName}) {
    return (
        <article className={styles['campaign-card']}>
            <h4>{campaignTitle}</h4>
            <p><span className={styles['region-wrapper']}>Регион:</span>{regionName}</p>
            <p className={styles['details-button']}>Details</p>
        </article>
    );
}
