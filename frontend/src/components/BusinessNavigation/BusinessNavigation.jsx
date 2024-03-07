import { Link } from 'react-router-dom';
import styles from '../MainNavigation.module.css';

export default function BusinessNavigation() {
    return (
        <div className={styles.navbarNav}>
            <Link to="/business" className={styles.navbarNavLink}>
                Vot3
            </Link>
            <nav className={styles.navbar}>
                <Link to="/business/login" className={styles.navbarNavLink}>
                    Влез
                </Link>
                <Link to="/business/register" className={styles.navbarNavLink}>
                    Регистрирай се
                </Link>
            </nav>
        </div>
    );
}
