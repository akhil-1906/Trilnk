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
```
API : http://localhost:8080

## 📌 API Endpoints

---

### ✅ **POST `/api/shorten`**

Create a new shortened URL.

## Request Body (JSON):
```json
{
  "url": "https://www.github.com"
}
```
## Response:
```json
{
  "shortCode": "IJ4x9nj"
}
```
### ✅ **GET `/api/shorten/{shortCode}`**

Redirect to the original URL for a given short code.



### ✅ **GET `/api/shorten`**

Get all shortened URLs.



### ✅ **DELETE `/api/shorten/{shortcode}`**

Delete a shortened URL by its short code.

## Response
```json
{
  "message": "Deleted successfully"
}
```

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](./LICENSE) file for details.
