import React, { createContext, useContext, useState } from 'react';
import { useDispatch } from 'react-redux';

import { authenticateUser } from '../services/apiService';
import { setCampaigns } from '../redux/campaigns/campaignsSlice';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [userPin, setUserPin] = useState(localStorage.getItem("userPin") || ""); //prettier-ignore
    const [isAdmin, setIsAdmin] = useState(localStorage.getItem('isAdmin'));
    const dispatch = useDispatch();

    const login = async (pin) => {
        try {
            const response = await authenticateUser(pin);

            const filteredCampaigns = response?.filteredCampaigns;

            if (response.status >= 400 && response.status < 500) {
                throw new Error({
                    message: response.message,
                    status: response.status,
                });
            }
            setUserPin(pin);
            setIsAdmin(response.isAdmin);
            localStorage.setItem('userPin', pin);
            localStorage.setItem('isAdmin', response.isAdmin);
            dispatch(setCampaigns(filteredCampaigns));
        } catch (error) {
            // console.error('Authentication failed:', error);
            throw error;
        }
    };

    const logout = () => {
        localStorage.removeItem('userPin');
        localStorage.removeItem('isAdmin');
        localStorage.removeItem('jwtToken');
        localStorage.removeItem('filteredCampaigns');
        setUserPin('');
        setIsAdmin(false);
        window.location.href = '/';
    };

    const contextValue = { userPin, isAdmin, login, logout };

    return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};

const useAuth = () => useContext(AuthContext);

export default useAuth;
