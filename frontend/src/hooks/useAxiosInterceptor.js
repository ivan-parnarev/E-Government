import axios from "axios";

axios.defaults.headers.common["Authorization"] =
  localStorage.getItem("jwtToken");

axios.interceptors.request.use(
  (config) => {
    const jwtToken = localStorage.getItem("jwtToken");

    if (jwtToken) {
      config.headers.authorization = `Bearer ${jwtToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axios;
