import { useScroll, motion, useTransform } from 'framer-motion';
import styles from '../../../pages/LandingPage/LandingPage.module.css';
import { useRef } from 'react';

export default function Feature({ id, heading, description, img }) {
    const ref = useRef(null);
    const { scrollYProgress } = useScroll({
        target: ref,
        offset: ['0 1', '1.33 1'],
    });

    const scaleProgress = useTransform(scrollYProgress, [0, 1], [0.8, 1]);
    const opacityProgress = useTransform(scrollYProgress, [0, 1], [0.6, 1]);

    const isEven = id % 2 === 0;

    return (
        <motion.div ref={ref} style={{ scale: scaleProgress, opacity: opacityProgress }}>
            <div className={`${styles.containerRow} ${isEven ? styles.mirror : ''}`}>
                <div className={styles.containerColumn}>
                    <h6 className={styles.featureHeading}>#{id} Feauture</h6>
                    <h2 className={styles.sHeading}>{heading}</h2>
                    <p className={styles.subHeading}>{description}</p>
                </div>
                <div className={styles.containerImg}>
                    <img className={styles.featureImg} src={img} alt="" />
                </div>
            </div>
        </motion.div>
    );
}
