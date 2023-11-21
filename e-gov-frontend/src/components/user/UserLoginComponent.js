import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import styles from "./UserLoginComponent.module.css";
import { useState } from "react";

function UserLoginComponent() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };
  return (
    <div>
      <h4>Влезте в профила си</h4>

      <InputGroup size="sm" className={styles.loginInputGroupContainer}>
        <div className={styles.loginInputGroup}>
          <p className={styles.loginInputGroupInputLabel}>Потребителско име:</p>
          <Form.Control
            value={username}
            onChange={handleUsernameChange}
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
          />
        </div>

        <div>
          <p className={styles.loginInputGroupInputLabel}>Парола:</p>
          <Form.Control
            type="password"
            value={password}
            onChange={handlePasswordChange}
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
          />
        </div>
      </InputGroup>
    </div>
  );
}

export default UserLoginComponent;
