import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import styles from './MainNavigation.module.css';
import { Link } from 'react-router-dom';
import useAuth from '../hooks/AuthContext';

export function MainNavigation() {
    const { userPin, isAdmin, logout } = useAuth();

    return (
        <div className={styles.navbarNav}>
            <Navbar className={styles.navbar}>
                <Container>
                    <Nav className="me-auto">
                        <Link to="/" className={styles.navbarNavLink}>
                            Начало
                        </Link>

                        <Link to="/active-campaigns" className={styles.navbarNavLink}>
                            Активни кампании
                        </Link>

                        {isAdmin && userPin === '1111111111' && (
                            <Link to="/create-campaign" className={styles.navbarNavLink}>
                                Създаване на кампания
                            </Link>
                        )}

                        {userPin && (
                            <Link to="/results" className={styles.navbarNavLink}>
                                Резултати
                            </Link>
                        )}

                        {!userPin && (
                            <Link to="/profile" className={styles.navbarNavLink}>
                                Вход
                            </Link>
                        )}

                        {userPin && (
                            <button onClick={logout} className={styles.navbarNavLink}>
                                Изход
                            </button>
                        )}
                    </Nav>
                </Container>
            </Navbar>
        </div>
    );
}
