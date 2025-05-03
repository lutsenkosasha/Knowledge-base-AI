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
  localStorage.setItem('token', data.token);
  return {
    id: data.id,
    email: data.email,
    token: data.token,
    isAdmin: data.isAdmin
  };
}

export const createUser = async (data: { email: string; password: string }) => {
  const res = await axios.post(`${API_BASE}/users`, data);
  return res.data;
};

export const getAllUsers = async () => {
  const res = await axios.get(`${API_BASE}/users`);
  return res.data;
};

export const updateUser = async (id: number, data: { email: string; password?: string }) => {
  const res = await axios.put(`${API_BASE}/users/${id}`, data);
  return res.data;
};

export const deleteUser = async (id: number) => {
  const res = await axios.delete(`${API_BASE}/users/${id}`);
  return res.data;
};