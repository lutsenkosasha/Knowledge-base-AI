import React, { useEffect, useState } from 'react';
import { createUser, deleteUser, getAllUsers, updateUser } from '../api/authApi';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import UserForm from '../components/UserForm';
import LogoutButton from '../components/LogoutButton';
import { UserFormData } from '../types/types'; 

interface User {
  id?: number;
  email: string;
  password?: string;
  name: string;
  surname?: string;
  department?: string;
  post?: string;
  isAdmin?: boolean;
}

const AdminPanelPage = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [newUser, setNewUser] = useState<User>({ id: 0, email: '', password: '', name: ''});
  const [editUser, setEditUser] = useState<User | null>(null);
  const currentUser = useSelector((state: RootState) => state.auth.user);
  console.log('Текущий пользователь:', currentUser);

  const fetchUsers = async () => {
    try {
      const userList = await getAllUsers();
      const adaptedUsers = userList.map((user: any) => ({
        ...user,
        id: user.userId,
      }));
      console.log('Список пользователей:', adaptedUsers);
      setUsers(adaptedUsers);
    } catch (error) {
      console.error('Ошибка при получении пользователей:', error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleSetNewUser = (userData: UserFormData) => {
    setNewUser(prev => ({ ...prev, ...userData }));
  };
  
  const handleSetEditUser = (userData: UserFormData) => {
    if (editUser) {
      setEditUser(prev => ({ ...prev!, ...userData }));
    }
  };

  const handleCreateUser = async () => {
    try {
      await createUser({ name: newUser.name, email: newUser.email, password: newUser.password || '' });
      setNewUser({ email: '', password: '', name: '' });
      fetchUsers();
    } catch (err) {
      console.error('Ошибка при создании пользователя:', err);
    }
  };

  const handleDeleteUser = async (id: number) => {
    if (!id) {
      console.error('Ошибка: нет ID пользователя для удаления');
      return;
    }
    try {
      await deleteUser(id);
      setUsers(users.filter((u) => u.id !== id));
    } catch (err) {
      console.error('Ошибка при удалении пользователя:', err);
    }
  };

  const handleUpdateUser = async () => {
    if (!editUser?.id) {
      console.error('Нет ID пользователя');
      return;
    }
    try {
      await updateUser(editUser.id, { email: editUser.email, password: editUser.password, name: editUser.name });
      setEditUser(null);
      fetchUsers();
    } catch (err) {
      console.error('Ошибка при обновлении пользователя:', err);
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h1>Админ-панель</h1>
      <h3>Текущий пользователь:</h3>
      {currentUser ? (
        <p>{currentUser.email} (ID: {currentUser.id})</p>
      ) : (
        <p>Пользователь не авторизован</p>
      )}

      <LogoutButton />
      <h3>Создать нового пользователя</h3>
      <UserForm
        user={newUser}
        setUser={handleSetNewUser}
        onSubmit={handleCreateUser}
        buttonLabel="Создать"
      />

      {editUser && (
        <>
          <h3>Редактировать пользователя</h3>
          <UserForm
            user={editUser}
            setUser={handleSetEditUser}
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
            <button onClick={() => setEditUser(user)} style={{ backgroundColor: '#FF8C00', color: 'white', border: 'none', padding: '0.3rem 0.7rem', borderRadius: '4px', marginLeft: '1rem', cursor: 'pointer' }}>
              Редактировать
            </button>
            <button onClick={() => handleDeleteUser(user.id!)} style={{ backgroundColor: '#B22222', color: 'white', border: 'none', padding: '0.3rem 0.7rem', borderRadius: '4px', marginLeft: '0.5rem', cursor: 'pointer' }}>
              Удалить
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminPanelPage;