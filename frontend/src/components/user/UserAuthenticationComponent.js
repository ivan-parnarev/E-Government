import UserGuestComponent from "./UserGuestComponent";
import styles from "./UserAuthenticationComponent.module.css";

function UserAuthenticationComponent() {
  return (
    <div className={styles.authenticationFormContainer}>
      <div>
        <UserGuestComponent />
      </div>
    </div>
  );
}

export default UserAuthenticationComponent;
