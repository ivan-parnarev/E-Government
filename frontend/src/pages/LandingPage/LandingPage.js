import styles from './LandingPage.module.css';
import { LandingPageNav } from '../../components/LandingPageNav/LandingPageNav';
import { Features } from '../../components/LandingPage/Features/Features';
import { LPHeader } from '../../components/LandingPage/LPHeader';
import { LPDescription } from '../../components/LandingPage/LPDescription';

export function LandingPage() {
    return (
        <>
            <LandingPageNav />

            <main className={styles.main}>
                <LPHeader />

                <LPDescription />

                <Features />
            </main>
        </>
    );
}
