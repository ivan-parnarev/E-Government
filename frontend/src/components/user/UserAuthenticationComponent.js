import UserGuestComponent from "./UserGuestComponent";
import UserLoginComponent from "./UserLoginComponent";
import styles from "./UserAuthenticationComponent.module.css";

function UserAuthenticationComponent() {
  return (
    <div className={styles.authenticationFormContainer}>
      <div>
        <UserLoginComponent />
        <p>или</p>
        <UserGuestComponent />
      </div>
    </div>
  );
}

export default UserAuthenticationComponent;
