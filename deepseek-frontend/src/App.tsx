import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Chat from './pages/Chat';
import AllDirectories from './pages/AllDirectories';
import Directory from './pages/Directory';
import LoginPage from './pages/LoginPage';
import AdminPanelPage from './pages/AdminPanelPage';
import './App.css';
import AllFiles from './pages/AllFiles';

function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/admin" element={<AdminPanelPage />} />
      <Route path="/chat/:id" element={<Chat />} />
      <Route path="/directories" element={<AllDirectories />} />
      <Route path="/directories/:id" element={<Directory />} />
      {/* <Route path="/files/:id" element={<AllFiles />} /> */}
    </Routes>
  );
}

export default App;