import React, { useState, useEffect } from 'react';
import axios from '../api/axiosInstance';
import {useParams} from 'react-router-dom';
import type { Message } from '../types/types';

const Chat = () => {
  const {id} = useParams();
  const [messages, setMessages] = useState<Message[]>([]);
  const [input, setInput] = useState('');
  const [sessionId, setSessionId] = useState<number | null>(null);
  const [sessionInfo, setSessionInfo] = useState({
    userName: '',
    directoryName: ''
  });


  useEffect(() => {
      const initializeSession = async () => {
          try {
              const response = await axios.post('/sessions', null, {
                  params: {
                      userId: 1,
                      directoryId: id
                  }
              });

              setSessionId(response.data.sessionId);
              setSessionInfo({
                  userName: response.data.userName,
                  directoryName: response.data.directoryName
              });

          } catch (error) {
              console.error('Ошибка при создании сессии:', error);
          }
      };
      if (id) {
        initializeSession();
      }
  }, [id]);


  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!input.trim() || !sessionId) return;

    try {
      await axios.post(`/chat/${sessionId}`, { message: input });

      const response = await axios.get(`/messages?sessionId=${sessionId}`);
      setMessages(response.data);

      setInput('');
    } catch (error) {
      console.error('Ошибка:', error);
    }
  };

  return (
    <div className="chat-container">
      <div className="chat-header">
        <h2>Чат с DeepSeek</h2>
        <div className="session-info">
          <span>Пользователь: {sessionInfo.userName}</span>
          <span>Директория: {sessionInfo.directoryName}</span>
        </div>
      </div>
      <div className="messages">
        {messages.map((msg) => (
          <div key={msg.messageId} className={`message ${msg.type}`}>
            {msg.text}
          </div>
        ))}
      </div>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Введите сообщение..."
        />
        <button type="submit">Отправить</button>
      </form>
    </div>
  );
};

export default Chat;
