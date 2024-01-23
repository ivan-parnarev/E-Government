import axios from "axios";
import API_URLS from "../utils/apiUtils";

export const authenticateUser = async (userPin) => {
  try {
    const response = await axios.post(API_URLS.AUTHENTICATE_USER, { userPin });
    return response.data;
  } catch (error) {
    console.error("Authentication failed:", error.response || error.message);
    throw error;
  }
};
