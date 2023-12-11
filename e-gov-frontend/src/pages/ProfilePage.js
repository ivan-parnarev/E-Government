import styles from "./ProfilePage.module.css";
import usePinInput from "../hooks/usePinInput";
import { useAuth } from "../hooks/AuthContext";
import Button from "react-bootstrap/Button";
import UserAuthenticationComponent from "../components/user/UserAuthenticationComponent";

export function ProfilePage() {
  const { userPin, login } = useAuth();
  const { pinValue, isValidPinValue, handlePinChange } = usePinInput();

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        {userPin ? (
          <p>This is the profile page. ✨</p>
        ) : (
          <div>
            <UserAuthenticationComponent
              pinValue={pinValue}
              isValidPinValue={isValidPinValue}
              onChange={handlePinChange}
            />
            <Button onClick={() => login(pinValue)}>Влез</Button>
          </div>
        )}
      </div>
    </div>
  );
}
