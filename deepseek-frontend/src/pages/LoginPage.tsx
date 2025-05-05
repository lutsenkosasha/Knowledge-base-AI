import React, { useState, FormEvent } from 'react';
import { useDispatch } from 'react-redux';
import { login } from '../redux/authSlice';
import { loginUser } from '../api/authApi';
import { parseJwt } from '../utils/jwtUtils';
import { useNavigate } from 'react-router-dom';
import '../styles/LoginPage.css'

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    try {
      const data = await loginUser(email, password);
      const parsed = parseJwt(data.token);
      dispatch(login(data));
      localStorage.setItem('token', data.token);
      navigate(parsed.isAdmin ? '/admin' : '/directories');
    } catch (err) {
      alert('Ошибка авторизации');
    }
  };

  return (
    <div className="auth-container">
      <form onSubmit={handleSubmit} className="auth-form">
        <h2>Вход</h2>
        <input type="text" placeholder="E-mail" value={email} onChange={e => setEmail(e.target.value)} />
        <input type="password" placeholder="Пароль" value={password} onChange={e => setPassword(e.target.value)} />
        <button type="submit">Войти</button>
      </form>
    </div>
  );
};

export default LoginPage;