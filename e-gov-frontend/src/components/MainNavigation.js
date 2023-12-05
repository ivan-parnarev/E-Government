import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import styles from "./MainNavigation.module.css";
import eGovLogo from "../assets/images/e-gov-logo.png";
import { Link } from "react-router-dom";

export function MainNavigation() {
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

            <Link
              to="/active-campaigns"
              className={`${styles.navbarNavLink} ${styles.activeCampaigns}`}
            >
              Активни кампании
            </Link>

            <Link to="/profile" className={styles.navbarNavLink}>
              Профил
            </Link>
          </Nav>
        </Container>
      </Navbar>
    </div>
  );
}
