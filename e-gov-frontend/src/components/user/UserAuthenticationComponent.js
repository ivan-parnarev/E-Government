import UserGuestComponent from "./UserGuestComponent";
import UserLoginComponent from "./UserLoginComponent";
import styles from "./UserAuthenticationComponent.module.css";

function UserAuthenticationComponent({ pinValue, isValidPinValue, onChange }) {
  return (
    <div className={styles.authenticationFormContainer}>
      <UserLoginComponent />
      <p>или</p>
      <UserGuestComponent
        pinValue={pinValue}
        isValidPinValue={isValidPinValue}
        onChange={onChange}
      />
    </div>
  );
}

export default UserAuthenticationComponent;
