import React, { createContext, useContext, useEffect, useState } from "react";
import { authenticateUser } from "../services/apiService";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [userPin, setUserPin] = useState(localStorage.getItem("userPin") || ""); //prettier-ignore
  const [isAdmin, setIsAdmin] = useState(localStorage.getItem("isAdmin") || "");

  const login = async (pin) => {
    try {
      const response = await authenticateUser(pin);
      setUserPin(pin);
      setIsAdmin(response.isAdmin);
      localStorage.setItem("userPin", pin);
      localStorage.setItem("isAdmin", response.isAdmin);
    } catch (error) {
      console.error("Authentication failed:", error);
      throw error;
    }
  };

  const logout = () => {
    localStorage.removeItem("userPin");
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
