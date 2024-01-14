import UserGuestComponent from "./UserGuestComponent";
import UserLoginComponent from "./UserLoginComponent";
import styles from "./UserAuthenticationComponent.module.css";

function UserAuthenticationComponent() {
  return (
    <div className={styles.authenticationFormContainer}>
      <UserLoginComponent />
      <p>или</p>
      <UserGuestComponent />
    </div>
  );
}

export default UserAuthenticationComponent;
