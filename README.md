# 🔗 Trilnk – URL Shortener

This is the service of **Trilnk**, a URL shortening platform built using **Java + Spring Boot** and **MongoDB**.

---

## ✅ Features

- Shorten long URLs into unique short codes  
- Redirect to original URL using short code  
- Persistent storage using MongoDB  
- RESTful API design  

---

## 🛠 Tech Stack

| Component     | Technology     |
|---------------|----------------|
| Framework     | Spring Boot    |
| Language      | Java 17+       |
| Database      | MongoDB        |
| Build Tool    | Maven          |
| Dependencies  | Spring Web, Spring Data MongoDB, Lombok |

---

## 🚀 Run with Docker

```bash
docker-compose up --build



## 📌 API Endpoints

---

### ✅ **POST `/api/shorten`**

Create a new shortened URL.

**Request Body (JSON):**
```json
{
  "url": "https://www.github.com"
}

**Response

<img width="1037" height="432" alt="Screenshot 2025-11-02 at 11 04 12 AM" src="https://github.com/user-attachments/assets/bf3dd96f-ca2c-48ed-9b76-3988f53e1055" />



### ✅ **GET `/api/shorten/{shortCode}`**

Redirect to the original URL for a given short code.



### ✅ **GET `/api/shorten`**

Get all shortened URLs.



### ✅ **DELETE `/api/shorten{shortcode}`**

**Response
### 📌 Response Example

![Response](https://github.com/user-attachments/assets/36f96b8c-aad4-4de1-a869-18004df02cdf)


