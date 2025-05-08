# KnowledgeBaseAI

Корпоративное веб-приложение с чат-ботом на базе ИИ для работы с базой знаний по транспорту и оборудованию.

## 🧠 Описание проекта

Приложение позволяет хранить и структурировать документы в тематических папках, а также взаимодействовать с ИИ-ботом, который отвечает на вопросы, используя содержимое загруженных файлов. Чат-бот работает на базе DeepSeek через локальный Ollama API (REST).

### Основная функциональность

- ⚙️ Авторизация:
  - Пользователь не может самостоятельно зарегистрироваться — это делает администратор.
  - При входе требуется авторизация. Без неё доступ невозможен.
  - Регистрация и вход с использованием JWT.
  - Хеширование паролей с использованием Bcrypt.
  - Роль `Administrator` была реализована, но позже удалена по решению команды.

- 🗂️ Работа с папками:
  - Отображение списка всех папок при успешном входе.
  - Возможность загрузки, удаления, перемещения и поиска файлов внутри папок.

- 💬 Чат-бот:
  - В каждой папке есть доступ к чат-боту.
  - Возможность начать новую сессию и возобновить старую.
  - Отображается история прошлых диалогов.

- 👤 Администрирование:
  - Удаление сотрудников.
  - Создание сотрудников.
  - Изменение информации о пользователях (должность, отдел и т.д.).   
    
- Аудит (`/api/audit`):
  - Пагинация логов аудита действий в системе.

## 🛠️ Технологии

**Backend:**
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- Bcrypt (для хеширования паролей)
- Hibernate
- Liquibase
- PostgreSQL
- Lombok
- Maven
- Postman (для тестирования)

**Frontend:**
- React
- Redux
- TypeScript
- Axios
- HTML / CSS

**ИИ и API:**
- DeepSeek + Ollama API (локально)

## 📡 REST API

### Аутентификация
- `POST /api/auth/login` — логин, возвращает JWT.

### Пользователи (только админ)
- `POST /api/users` — создать одного пользователя.
- `POST /api/users/batch` — создать сразу несколько.
- `GET /api/users/{id}` — получить по ID.
- `GET /api/users` — получить всех.
- `PUT /api/users/{id}` — обновить данные.
- `DELETE /api/users/{id}` — удалить пользователя.

### Сессии и чат
- `POST /api/sessions?userId=...&directoryId=...` — создать или получить сессию.
- `POST /api/sessions/{sessionId}/messages` — отправить сообщение в сессию.
- `GET /api/sessions` — получить все сессии.
- `GET /api/sessions/{id}` — получить сессию по ID.
- `PUT /api/sessions/{id}` — обновить сессию.
- `DELETE /api/sessions/{id}` — удалить сессию.
- `POST /api/chat/{sessionId}` — отправить сообщение и получить ответ от ИИ.

### Каталоги
- `GET /api/directories/findAll` — получить все каталоги.
- `GET /api/directories/{id}` — получить каталог по ID.
- `GET /api/directories/findByDepartment?department=HR` — получить каталоги по департаменту.
- `POST /api/directories` — создать новый каталог.
- `PUT /api/directories/{id}` — обновить каталог по ID.
- `DELETE /api/directories/{id}` — удалить каталог по ID.

### Файлы
- `GET /api/files` — получить все файлы.
- `GET /api/files/{directoryId}` — получить файлы из конкретного каталога.
- `POST /api/files` — создать новый файл.
- `PUT /api/files/{id}` — обновить файл по ID.
- `DELETE /api/files/{id}` — удалить файл по ID.

### База знаний
- `POST /api/knowledge-base` — создать новую запись.
- `POST /api/knowledge-base/batch` — создать несколько записей сразу.
- `GET /api/knowledge-base` — получить все записи.
- `GET /api/knowledge-base/{id}` — получить запись по ID.
- `PUT /api/knowledge-base/{id}` — обновить запись по ID.
- `DELETE /api/knowledge-base/{id}` — удалить запись по ID.

## 🧪 Тестирование

Тестирование всех эндпоинтов проводилось с помощью **Postman**.


## 🚀 Запуск проекта

1. Склонируй репозиторий:
    ```bash
    git clone https://github.com/your-username/chat-assistant-api.git
    ```
2. Перейди в папку backend:
   ```bash
   cd backend
   ```
3. Укажи параметры подключения к базе данных в `application.properties`.

4. Собери и запусти проект:
    ```bash
    mvn spring-boot:run
    ```
5. Перейди в папку deepseek-frontend:
   ```bash
   cd deepseek-frontend
   ```
6. Обнови зависимости и запусти:
   ```bash
   npm install
   npm start
   ```
