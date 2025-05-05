import { parseJwt } from '../utils/jwtUtils';
import axios from './axiosInstance';

const API_BASE = '/api';

export async function loginUser(email: string, password: string) {
  const response = await fetch(`${API_BASE}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });
  if (!response.ok) {
    throw new Error('Invalid credentials');
  }
  const data = await response.json();
  const token = data.token;

  const payload = parseJwt(token);
  const user = {
    id: payload?.userId,
    email: payload?.sub,
    isAdmin: payload?.isAdmin,
    token
  };

  localStorage.setItem('token', token);
  localStorage.setItem('user', JSON.stringify(user));

  return user;
}

const getAuthHeaders = () => {
  const token = localStorage.getItem('token');
  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  };
};

export const createUser = async (data: { email: string; password: string, name: string }) => {
  const res = await axios.post(`${API_BASE}/users`, data, getAuthHeaders());
  return res.data;
};

export const getAllUsers = async () => {
  const res = await axios.get(`${API_BASE}/users`, getAuthHeaders());
  return res.data;
};

export const updateUser = async (id: number, data: { email: string; password?: string, name?: string }) => {
  const res = await axios.put(`${API_BASE}/users/${id}`, data, getAuthHeaders());
  return res.data;
};

export const deleteUser = async (id: number) => {
  const res = await axios.delete(`${API_BASE}/users/${id}`, getAuthHeaders());
  return res.data;
};