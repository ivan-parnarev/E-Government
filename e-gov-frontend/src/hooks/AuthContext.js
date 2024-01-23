import React, { createContext, useContext, useState } from "react";
import { authenticateUser } from "../services/apiService";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [userPin, setUserPin] = useState(sessionStorage.getItem("userPin") || ""); //prettier-ignore

  const login = async (pin) => {
    try {
      const response = await authenticateUser(pin);
      setUserPin(response.userPin);
      sessionStorage.setItem("userPin", response.userPin);
    } catch (error) {
      console.error("Authentication failed:", error);
      throw error;
    }
  };

  const logout = () => {
    sessionStorage.removeItem("userPin");
    setUserPin("");
  };

  return (
    <AuthContext.Provider value={{ userPin, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

const useAuth = () => useContext(AuthContext);

export default useAuth;
