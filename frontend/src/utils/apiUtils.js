const API_BASE_URL = "http://localhost:8080/api/v1";

const API_URLS = {
  VOTE: `${API_BASE_URL}/vote`,
  CENSUS: `${API_BASE_URL}/census`,
  ACTIVE_VOTE: `${API_BASE_URL}/campaigns/active/vote`,
  ACTIVE_CENSUS: `${API_BASE_URL}/campaigns/active/census`,
  CREATE_VOTE: `${API_BASE_URL}/campaigns/create/vote`,
  CREATE_CENSUS: `${API_BASE_URL}/campaigns/create/census`,
  AUTHENTICATE_USER: `${API_BASE_URL}/authenticate`,
  GET_REGIONS: `${API_BASE_URL}/regions`,
};

export default API_URLS;
