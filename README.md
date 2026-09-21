## Запуск проєкту

Для запуску проєкту необхідно встановити **Docker**.

### 1. Клонування репозиторію

Клонуйте репозиторій:

```bash
git clone <URL_РЕПОЗИТОРІЮ>
```

Перейдіть у директорію проєкту:

```bash
cd <PROJECT_DIRECTORY>
```

### 2. Запуск Docker-контейнерів

Виконайте команду:

```bash
docker-compose up --build
```
# API

## Register

### Endpoint

```http
POST http://localhost/api/auth/register
```

### Вхідні дані

Для реєстрації необхідно передати `email` і `password` у форматі JSON:

```json
{
  "email": "user@gmail.com",
  "password": "password123"
}
```

### Відповідь

При успішній реєстрації сервер не повертає даних у тілі відповіді.

HTTP status:

```text
201 Created
```

### Приклад

![Register](images/register.png)

---

## Login

### Endpoint

```http
POST http://localhost/api/auth/login
```

### Вхідні дані

Для авторизації необхідно передати `email` і `password`:

```json
{
  "email": "user@gmail.com",
  "password": "password123"
}
```

### Відповідь

У відповідь сервер повертає `token` та `uid` користувача:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "uuid": "550e8400-e29b-41d4-a716-446655440000"
}
```

### Приклад

![Login](images/login.png)

---

## Process

### Endpoint

```http
POST http://localhost/api/process
```
Для запиту, необхідно передати в заголовку `Authorization` токен з префіксом `Bearer`.

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### Приклад

![Authorization Header](images/headers.png)

### Вхідні дані

Для виконання запиту необхідно передати `UUID` користувача та текст:

```json
{
  "UUID": "550e8400-e29b-41d4-a716-446655440000",
  "text": "Hello World"
}
```

### Відповідь

Сервер повертає переданий текст у зміненому вигляді: текст обертається у зворотному порядку та переводиться у верхній регістр.

```json
{
  "result": "DLROW OLLEH"
}
```

### Приклад

![Process](images/process.png)
