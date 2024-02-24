import styles from "./ProfilePage.module.css";
import useAuth from "../hooks/AuthContext";
import UserAuthenticationComponent from "../components/user/UserAuthenticationComponent";
import UserProfileComponent from "../components/user/UserProfileComponent";

export function ProfilePage() {
  const { userPin } = useAuth();

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        {userPin ? (
          <UserProfileComponent />
        ) : (
          <div>
            <UserAuthenticationComponent />
          </div>
        )}
      </div>
    </div>
  );
}
