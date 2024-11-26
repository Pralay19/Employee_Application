import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/iiitb/Employee';

export const loginEmployee = (data) => axios.post(`${API_BASE_URL}/login`, data);
export const registerEmployee = (formData) => axios.post(`${API_BASE_URL}/register`, formData, {
  headers: { 'Content-Type': 'multipart/form-data' }
});
export const getProfile = (email, token) => axios.get(`${API_BASE_URL}/profile/${email}`, {
  headers: { Authorization: `Bearer ${token}` }
});
export const updateProfile = (email, formData, token) => axios.post(`${API_BASE_URL}/Profile/updateinfo`, formData, {
  headers: { Authorization: `Bearer ${token}`, 'Content-Type': 'multipart/form-data' }
});
