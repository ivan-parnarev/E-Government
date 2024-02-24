import { motion } from 'framer-motion';
import styles from '../../pages/LandingPage/LandingPage.module.css';

export function LPHeader() {
    return (
        <motion.section
            initial={{ opacity: 0, y: 100 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.3 }}
        >
            <h1 className={styles.heading}>
                Получете ценна информация от вашите клиенти с Vot3 проучвания.
            </h1>

            <p className={styles.subHeading}>
                Vot3 помага на бизнеса да събира обратна връзка и мнения директно от целевата си
                аудитория. С нашата лесна за използване платформа можете да създавате и
                разпространявате анкети дa придобийте ценна информация, която стимулира растежа на
                бизнеса.
            </p>
        </motion.section>
    );
}
