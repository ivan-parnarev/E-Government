import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import styles from "./MainNavigation.module.css";
import eGovLogo from "../assets/Government-PNG-Image-HD.png";

function MainNavigation() {
  return (
    <div className={styles.navbarNav}>
      <Navbar className={styles.navbar}>
        <Container>
          <Navbar.Brand
            href="#home"
            className={styles.navbarLogoImageContainer}
          >
            <img src={eGovLogo} alt="logo" className={styles.navbarLogoImage} />
          </Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="#home" className={styles.navbarNavLink}>
              Home
            </Nav.Link>
            <Nav.Link href="#register" className={styles.navbarNavLink}>
              Register
            </Nav.Link>
            <Nav.Link
              href="#active-campaigns"
              className={`${styles.navbarNavLink} ${styles.activeCampaigns}`}
            >
              Active Campaigns
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </div>
  );
}

export default MainNavigation;
