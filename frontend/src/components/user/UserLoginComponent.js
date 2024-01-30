import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
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

      <FloatingLabel label="Потребителско име:" className="mb-3">
        <Form.Control
          className={styles.loginInputGroupInputLabel}
          type="text"
          placeholder=""
          value={username}
          onChange={handleUsernameChange}
        />
      </FloatingLabel>

      <FloatingLabel label="Парола:" className="mb-3">
        <Form.Control
          className={styles.loginInputGroupInputLabel}
          type="password"
          placeholder=""
          value={password}
          onChange={handlePasswordChange}
        />
      </FloatingLabel>
    </div>
  );
}

export default UserLoginComponent;
