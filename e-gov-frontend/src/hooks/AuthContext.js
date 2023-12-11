import React, { createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [userPin, setUserPin] = useState(sessionStorage.getItem("userPin") || ""); //prettier-ignore
  const login = (pin) => {
    sessionStorage.setItem("userPin", pin);
    setUserPin(pin);
  };

  const logout = (pin) => {
    sessionStorage.removeItem("userPin");
    setUserPin("");
  };

  return (
    <AuthContext.Provider value={{ userPin, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
