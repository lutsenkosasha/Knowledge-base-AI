import { configureStore } from '@reduxjs/toolkit';
import authReducer from './authSlice';

const savedUser = localStorage.getItem('user');

const preloadedState = savedUser
  ? {
      auth: {
        user: JSON.parse(savedUser),
        isAuthenticated: true,
      },
    }
  : undefined;

export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
  preloadedState,
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;