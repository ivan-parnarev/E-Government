import styles from "./ProfilePage.module.css";
import useAuth from "../hooks/AuthContext";
import UserAuthenticationComponent from "../components/user/UserAuthenticationComponent";

export function ProfilePage() {
  const { userPin } = useAuth();

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        {userPin ? (
          <p style={{ textAlign: "center", fontSize: "1.5rem" }}>
            Влезнахте успешно в профила си.
          </p>
        ) : (
          <div>
            <UserAuthenticationComponent />
          </div>
        )}
      </div>
    </div>
  );
}
