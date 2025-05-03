import React, { useEffect, useState } from 'react';
import { createUser, deleteUser, getAllUsers, updateUser } from '../api/authApi';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import UserForm from '../components/UserForm';
import LogoutButton from '../components/LogoutButton';

interface User {
  id: number;
  email: string;
  password?: string;
}

const AdminPanelPage = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [newUser, setNewUser] = useState<User>({ id: 0, email: '', password: '' });
  const [editUser, setEditUser] = useState<User | null>(null);
  const currentUser = useSelector((state: RootState) => state.auth.user);

  const fetchUsers = async () => {
    try {
      const userList = await getAllUsers();
      setUsers(userList);
    } catch (error) {
      console.error('Ошибка при получении пользователей:', error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleCreateUser = async () => {
    try {
      await createUser({ email: newUser.email, password: newUser.password || '' });
      setNewUser({ id: 0, email: '', password: '' });
      fetchUsers();
    } catch (err) {
      console.error('Ошибка при создании пользователя:', err);
    }
  };

  const handleDeleteUser = async (id: number) => {
    try {
      await deleteUser(id);
      setUsers(users.filter((u) => u.id !== id));
    } catch (err) {
      console.error('Ошибка при удалении пользователя:', err);
    }
  };

  const handleUpdateUser = async () => {
    if (!editUser) return;
    try {
      await updateUser(editUser.id, { email: editUser.email, password: editUser.password });
      setEditUser(null);
      fetchUsers();
    } catch (err) {
      console.error('Ошибка при обновлении пользователя:', err);
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h2>Админ-панель</h2>
      <p>Текущий пользователь: <strong>{currentUser?.email}</strong></p>

      <h3>Создать нового пользователя</h3>
      <UserForm
        user={newUser}
        setUser={setNewUser}
        onSubmit={handleCreateUser}
        buttonLabel="Создать"
      />

      {editUser && (
        <>
          <h3>Редактировать пользователя</h3>
          <UserForm
            user={editUser}
            setUser={setEditUser}
            onSubmit={handleUpdateUser}
            buttonLabel="Сохранить"
          />
        </>
      )}

      <h3>Список пользователей</h3>
      <ul>
        {users.map((user) => (
          <li key={user.id} style={{ marginBottom: '0.5rem' }}>
            <strong>{user.email}</strong>
            <button onClick={() => setEditUser(user)} style={{ marginLeft: '1rem' }}>
              Редактировать
            </button>
            <button onClick={() => handleDeleteUser(user.id)} style={{ marginLeft: '0.5rem' }}>
              Удалить
            </button>
          </li>
        ))}
      </ul>

      <LogoutButton />
    </div>
  );
};

export default AdminPanelPage;