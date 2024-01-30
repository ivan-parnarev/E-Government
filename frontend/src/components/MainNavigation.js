import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import styles from "./MainNavigation.module.css";
import eGovLogo from "../assets/images/e-gov-logo.png";
import { Link } from "react-router-dom";
import useAuth from "../hooks/AuthContext";

export function MainNavigation() {
  const { userPin, logout } = useAuth();

  return (
    <div className={styles.navbarNav}>
      <Navbar className={styles.navbar}>
        <Container>
          <Link to="/" className={styles.navbarLogoImageContainer}>
            <img src={eGovLogo} alt="logo" className={styles.navbarLogoImage} />
          </Link>

          <Nav className="me-auto">
            <Link to="/" className={styles.navbarNavLink}>
              Начало
            </Link>

            <Link to="/active-campaigns" className={styles.navbarNavLink}>
              Активни кампании
            </Link>

            {userPin == "1111111111" && (
              <Link to="/create-campaign" className={styles.navbarNavLink}>
                Създаване на кампания
              </Link>
            )}

            {userPin && (
              <Link to="/results" className={styles.navbarNavLink}>
                Резултати
              </Link>
            )}

            <Link to="/profile" className={styles.navbarNavLink}>
              Профил
            </Link>

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
