import styles from '../../../pages/LandingPage/LandingPage.module.css';
import { featuresData } from '../../../utils/features';
import Feature from './Feature';

export function Features() {
    return (
        <section className={`${styles.section} ${styles.features}`}>
            <div className={styles.container}>
                {featuresData.map((feature) => (
                    <Feature
                        key={feature.id}
                        id={feature.id}
                        heading={feature.heading}
                        description={feature.description}
                        img={feature.img}
                    />
                ))}
            </div>
        </section>
    );
}
