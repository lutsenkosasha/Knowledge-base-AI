import React from 'react';

interface Props {
  user: { email: string; password?: string };
  setUser: (user: any) => void;
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
      style={{ display: 'flex', flexDirection: 'column', maxWidth: '300px', marginBottom: '2rem' }}
    >
      <input
        type="text"
        placeholder="E-mail"
        value={user.email}
        onChange={(e) => setUser({ ...user, email: e.target.value })}
        required
      />
      <input
        type="password"
        placeholder="Пароль"
        value={user.password || ''}
        onChange={(e) => setUser({ ...user, password: e.target.value })}
        required
      />
      <button type="submit" style={{ marginTop: '1rem' }}>
        {buttonLabel}
      </button>
    </form>
  );
};

export default UserForm;