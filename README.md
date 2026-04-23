# 👤 유저(User) API 명세서

### 1. 유저 생성 (Create)

새로운 유저를 등록합니다. (회원가입)

| **항목** | **내용** |
| --- | --- |
| **기능** | 회원가입 |
| **Method** | `POST` |
| **URL** | `/users` |

**Request Body**

```json
{
  "userName": "채원",
  "email": "chaewon@naver.com",
  "password": "password123!"
}
```

**Response Body (201 Created)**

```json
{
  "id": 1,
  "userName": "채원",
  "email": "chaewon@naver.com",
  "createdAt": "2026-04-23T13:00:00",
  "modifiedAt": "2026-04-23T13:00:00"
}
```

---

### 2. 유저 로그인 (Login)

이메일과 비밀번호를 검증하여 세션을 생성합니다.

| **항목** | **내용** |
| --- | --- |
| **기능** | 로그인 및 세션 발급 |
| **Method** | `POST` |
| **URL** | `/users/login` |

**Request Body**

```json
{
  "email": "chaewon@naver.com",
  "password": "password123!"
}
```

**Response Body (200 OK)**

```
"로그인 성공! 유저 ID: 1"`
```
---

### 3. 유저 로그아웃 (Logout)

생성된 세션을 파기합니다.

| **항목** | **내용** |
| --- | --- |
| **기능** | 로그아웃 처리 |
| **Method** | `POST` |
| **URL** | `/users/logout` |

**Response Body (200 OK)**

```
"로그아웃 성공"`
```

---

### 4. 전체 유저 목록 조회 (Read)

시스템에 등록된 모든 유저를 조회합니다.

| **항목** | **내용** |
| --- | --- |
| **기능** | 유저 전체 조회 |
| **Method** | `GET` |
| **URL** | `/users` |

**Response Body (200 OK)**

```json
[
  {
    "id": 1,
    "userName": "채원",
    "email": "chaewon@naver.com",
    "createdAt": "2026-04-23T13:00:00"
  }
]
```

---

## 📅 일정(Schedule) API 명세서

### 1. 일정 생성 (Create)

새로운 일정을 등록합니다. (**로그인 세션 필수**)

| **항목** | **내용** |
| --- | --- |
| **기능** | 일정 작성 |
| **Method** | `POST` |
| **URL** | `/schedules` |

**Request Header**

- `Cookie: JSESSIONID=...`

**Request Body**

```json
{
  "title": "API 명세서 작성",
  "content": "양식에 맞춰서 상세히 작성하기"
}
```

**Response Body (201 Created)**

```json
{
  "id": 1,
  "title": "API 명세서 작성",
  "content": "양식에 맞춰서 상세히 작성하기",
  "userName": "채원",
  "createdAt": "2026-04-23T13:50:00"
}
```

---

### 2. 선택 일정 수정 (Update)

특정 일정을 수정합니다. (**작성자 본인 세션 필수**)

| **항목** | **내용** |
| --- | --- |
| **기능** | 일정 내용 수정 |
| **Method** | `PATCH` |
| **URL** | `/schedules/{id}` |

**Request Body**

```json
{
  "title": "수정된 일정 제목",
  "content": "수정된 내용입니다."
}
```

**Response Body (200 OK)**

```json
{
  "id": 1,
  "title": "수정된 일정 제목",
  "content": "수정된 내용입니다.",
  "modifiedAt": "2026-04-23T13:55:00"
}
```

---

### 3. 선택 일정 삭제 (Delete)

특정 일정을 삭제합니다. (**작성자 본인 세션 필수**)

| **항목** | **내용** |
| --- | --- |
| **기능** | 일정 삭제 |
| **Method** | `DELETE` |
| **URL** | `/schedules/{id}` |

**Response Body (200 OK)**

```
"삭제가 완료되었습니다."`
```