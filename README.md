 🛠️ Complaint Management System (Flatmates / Hostel)

A backend project built using **Spring Boot** for managing hostel or PG-related complaints. Users can register, login, raise complaints, and view status updates. Admins can resolve complaints, filter by status, and view analytics.

---

## 🚀 Features

### 👤 User Role
- Register & Login with JWT
- Raise a complaint
- View their complaints (paginated)
- Update/Delete their own complaints

### 🛡️ Admin Role
- View all complaints (paginated)
- Filter by status: `Pending`, `In-Progress`, `Resolved`
- Change complaint status
- View analytics (pending/resolved count)

---

## 🧱 Tech Stack

- **Backend**: Spring Boot
- **Database**: MongoDB
- **Security**: Spring Security + JWT
- **Password Encryption**: BCrypt
- **Documentation**: Swagger UI (optional)

---

## 🔐 Authentication

- JWT-based login and access
- Role-based access control (USER / ADMIN)
- Secured endpoints using Spring Security

---

## 📊 Analytics

Admin can access an endpoint to see total:
- Pending complaints
- In-progress complaints
- Resolved complaints

---

## 📄 API Endpoints

### 🔐 Auth
- `POST /api/auth/register` – Register user
- `POST /api/auth/login` – Login and get JWT

### 📝 Complaints
- `POST /api/complaints` – Create complaint
- `GET /api/complaints/my?page=0&size=5` – User's complaints
- `GET /api/complaints?page=0&size=5` – All complaints (admin)
- `PUT /api/complaints/{id}` – Update complaint
- `PUT /api/complaints/{id}/status` – Update status (admin)
- `DELETE /api/complaints/{id}` – Delete complaint
- `GET /api/complaints/analytics` – Complaint stats

---

## 🔧 How to Run

1. Clone the repo:
```bash
git clone https://github.com/yourusername/complaint-management-system.git
cd complaint-management-system
