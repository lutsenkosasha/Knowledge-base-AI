import React from 'react';
import { UserFormData } from '../types/types'; 
interface Props {
  user: UserFormData;
  setUser: (user: UserFormData) => void;
  onSubmit: () => void;
  buttonLabel: string;
}

const UserForm: React.FC<Props> = ({ user, setUser, onSubmit, buttonLabel }) => {
  return (
    <form
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit();
      }}
      style={{
        display: 'flex',
        flexDirection: 'column',
        maxWidth: '300px',
        marginBottom: '2rem',
        gap: '0.75rem',
      }}
    >
      <input
        type="text"
        placeholder="Имя"
        value={user.name || ''}
        onChange={(e) => setUser({ ...user, name: e.target.value })}
        required
        style={{ padding: '0.5rem', width: '94%' }}
      />
      <input
        type="text"
        placeholder="E-mail"
        value={user.email}
        onChange={(e) => setUser({ ...user, email: e.target.value })}
        required
        style={{ padding: '0.5rem', width: '94%' }}
      />
      <input
        type="password"
        placeholder="Пароль"
        value={user.password || ''}
        onChange={(e) => setUser({ ...user, password: e.target.value })}
        required
        style={{ padding: '0.5rem', width: '94%' }}
      />
      <button type="submit" style={{ marginTop: '1rem' }}>
        {buttonLabel}
      </button>
    </form>
  );
};

export default UserForm;