# Todo List Backend API - Spring Boot

Projek backend untuk aplikasi Todo List sederhana yang dibangun menggunakan **Kotlin** dan **Spring Boot**. API ini mendukung operasi CRUD (Create, Read, Update, Delete) untuk tugas (Todos) dan kategori (Categories).

## 🚀 Tech Stack

*   **Language:** Kotlin
*   **Framework:** Spring Boot 3.4.x
*   **Database:** MySQL
*   **ORM:** Spring Data JPA (Hibernate)
*   **Build Tool:** Gradle (Kotlin DSL)

## 🛠️ Prerequisities

Pastikan Anda sudah menginstal:
*   Java JDK 17 atau versi terbaru
*   MySQL Server (XAMPP / MySQL Installer)
*   Postman (untuk testing API)

## ⚙️ Configuration

Konfigurasi database berada di `src/main/resources/application.properties`.

**Default Settings:**
*   **MySQL Port:** `3307` (atau sesuaikan dengan XAMPP Anda)
*   **Database Name:** `todo_db` (akan dibuat otomatis jika belum ada)
*   **Server Port:** `8080`

## 🏃 How to Run

1.  Pastikan MySQL Service sudah berjalan.
2.  Buka terminal di root folder projek.
3.  Jalankan perintah berikut:
    ```bash
    ./gradlew bootRun
    ```
4.  Aplikasi akan berjalan di `http://localhost:8080`.

## 📌 API Endpoints

Semua response API dibungkus dalam format standard:
```json
{
  "success": true,
  "status": 200,
  "message": "English Message",
  "data": { ... },
  "errors": null
}
```

### 📁 Category Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/categories` | Ambil semua kategori |
| **GET** | `/api/categories/{id}` | Ambil satu kategori berdasarkan ID |
| **POST** | `/api/categories` | Tambah kategori baru |
| **PUT** | `/api/categories/{id}` | Update nama kategori |
| **DELETE** | `/api/categories/{id}` | Hapus kategori |

### 📝 Todo Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/todos` | Ambil semua tugas |
| **GET** | `/api/todos/{id}` | Ambil tugas berdasarkan ID |
| **GET** | `/api/todos/category/{categoryId}` | Ambil tugas berdasarkan Kategori |
| **POST** | `/api/todos` | Tambah tugas baru |
| **PUT** | `/api/todos/{id}` | Update data/status tugas |
| **DELETE** | `/api/todos/{id}` | Hapus tugas |

---
**Author:** ESA (Project AFL-3)
