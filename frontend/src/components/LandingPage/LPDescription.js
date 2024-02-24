import { motion, useScroll, useTransform } from 'framer-motion';
import styles from '../../pages/LandingPage/LandingPage.module.css';
import productImg from '../../assets/images/product-shot-p-800.png';
import { useRef } from 'react';

export function LPDescription() {
    const ref = useRef(null);
    const { scrollYProgress } = useScroll({
        target: ref,
        offset: ['0 1', '1.33 1'],
    });

    const scaleProgress = useTransform(scrollYProgress, [0, 1], [0.8, 1]);
    const opacityProgress = useTransform(scrollYProgress, [0, 1], [0.6, 1]);
    return (
        <motion.section
            className={styles.section}
            initial={{ opacity: 0, y: 100 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.175, duration: 0.3 }}
        >
            <img className={styles.image} src={productImg} alt="" />
            <motion.div ref={ref} style={{ scale: scaleProgress, opacity: opacityProgress }}>
                <h2 className={styles.sHeading}>
                    Отключете ценни прозрения с насочени проучвания.
                </h2>
                <p className={styles.subHeading}>
                    Vot3 позволява на бизнеса да събира обратна връзка и прозрения от целевата си
                    аудитория чрез персонализирани проучвания. Като раздавате въпроси на правилните
                    хора, можете да вземате решения, базирани на данни, и да подобрите своите
                    продукти, услуги и удовлетвореност на клиентите.
                </p>
            </motion.div>
        </motion.section>
    );
}
