import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface AuthState {
  user: {
    id: number;
    email: string;
    isAdmin: boolean;
    token: string;
  } | null;
  isAuthenticated: boolean;
}

const initialState: AuthState = {
  user: null,
  isAuthenticated: false,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    login(state, action: PayloadAction<{ id: number; email: string; isAdmin: boolean; token: string }>) {
      state.user = {
        id: action.payload.id,
        email: action.payload.email,
        isAdmin: action.payload.isAdmin,
        token: action.payload.token,
      };
      state.isAuthenticated = true;
    },
    logout(state) {
      state.user = null;
      state.isAuthenticated = false;
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;