import { Link } from 'react-router-dom';
import styles from './LandingPageNav.module.css';
import { Nav, Navbar } from 'react-bootstrap';
import { motion } from 'framer-motion';

export function LandingPageNav() {
    return (
        <motion.div
            className={styles.navbarNav}
            initial={{ y: -100, opacity: 0 }}
            animate={{ y: 0, opacity: 1 }}
            transition={{ duration: 0.2 }}
        >
            <Navbar className={styles.navbar}>
                <Nav className="me-auto">
                    <Link to="/business" className={styles.link}>
                        За Бизнеса
                    </Link>
                    <Link to="/government" className={styles.link}>
                        За Държавния Сектор
                    </Link>
                </Nav>
            </Navbar>
        </motion.div>
    );
}
