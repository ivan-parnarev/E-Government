import React, { createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [userPin, setUserPin] = useState(sessionStorage.getItem("userPin") || ""); //prettier-ignore

  const login = (pin) => {
    sessionStorage.setItem("userPin", pin);
    setUserPin(pin);
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
