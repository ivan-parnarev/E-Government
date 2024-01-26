import React, { createContext, useContext, useState } from "react";
import { authenticateUser } from "../services/apiService";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [userPin, setUserPin] = useState(sessionStorage.getItem("userPin") || ""); //prettier-ignore
  const [isAdmin, setIsAdmin] = useState(false);

  const login = async (pin) => {
    try {
      const response = await authenticateUser(pin);
      setUserPin(pin);
      setIsAdmin(response.isAdmin);
      sessionStorage.setItem("userPin", pin);
    } catch (error) {
      console.error("Authentication failed:", error);
      throw error;
    }
  };

  const logout = () => {
    sessionStorage.removeItem("userPin");
    localStorage.removeItem("jwtToken");
    setUserPin("");
    setIsAdmin(false);
  };

  const contextValue = { userPin, isAdmin, login, logout };

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

const useAuth = () => useContext(AuthContext);

export default useAuth;
