import styles from "./ProfilePage.module.css";
import useAuth from "../hooks/AuthContext";
import UserAuthenticationComponent from "../components/user/UserAuthenticationComponent";

export function ProfilePage() {
  const { userPin } = useAuth();

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        {userPin ? (
          <p style={{ textAlign: "center" }}>This is the profile page. ✨</p>
        ) : (
          <div>
            <UserAuthenticationComponent />
          </div>
        )}
      </div>
    </div>
  );
}
