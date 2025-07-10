# Inventory System

Project ini adalah sistem backend yang digunakan untuk mengembangkan aplikasi inventori barang. Sistem ini memungkinkan pengguna untuk menambahkan stok barang, melihat daftar barang, memperbarui stok, serta menghapus data barang.

---

## ✨ Fitur

- CRUD Barang
- Upload Gambar (format JPG/PNG)
- Validasi MIME Type Gambar
- Simpan path gambar di folder lokal
- Logging setiap request & response menggunakan Log4j2
- Simpan metadata dinamis (dalam bentuk JSON) ke database

---

## 🧰 Tech Stack

- Java 17
- Spring Boot Framework
- Maven
- Lombok
- Log4j2
- PostgreSQL

---

## 🛠️ Instalasi

### 1. Clone Project

```bash
https://github.com/juliaagnes16/inventory.git
cd inventory
```
### 2. Run Locally
```bash
./mvnw spring-boot:run
```
### 3. Setup Database PostgreSQL
```bash
CREATE DATABASE inventory;
```

---

## 📬 Postman Collection

### Endpoints Summary
| Method | URL | Description |
|---|---|---|
| GET | /api/stocks | List semua stok barang |
| GET | /api/stocks/{id} | Get detail barang |
| POST | /api/stocks | Membuat stok barang |
| PUT | /api/stocks/{id} | Update stok |
| DELETE | /api/stocks/{id} | Delete stok |

### POST/PUT request format: `multipart/form-data`
| Key | Type | Example |
|---|---|---|
| namaBarang | text | Tablet Samsung Galaxy Tab S8 |
| nomorSeri | text | SGT8877665544 |
| jumlahStok | text | 13 |
| additionalInfo | text | {"garansi": "1 tahun", "lokasi": "gudang A"} |
| gambar | file | upload gambar JPG/PNG |
| createdBy | text | John Doe |

### File Gambar
- Ukuran maksimum upload gambar dibatasi hingga 5MB. Jika lebih dari itu, request akan ditolak otomatis oleh server.
- File gambar akan disimpan di dalam folder upload/ di dalam project root, dan hanya path-nya yang akan disimpan di database.
[JPG](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcGehYJJfX1s1101GYrkmvkLj-zOtAMGgKWA&s)
[PNG](https://png.pngtree.com/png-vector/20231127/ourmid/pngtree-test-red-flat-icon-isolated-product-png-image_10722512.png)
