# Hotel Room Booking System

Full-stack hotel room booking project with:
- `booking` (Spring Boot + MySQL backend)
- `hotel-frontend` (React + Vite frontend)

## Project Structure

- `booking/` - backend API and database logic
- `hotel-frontend/` - frontend UI

## Prerequisites

- Java 17+
- Maven (or Maven Wrapper)
- Node.js 18+
- MySQL 8+

## 1) Database Setup

Run in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS hotel_booking_db;
```

Then update backend config in `booking/src/main/resources/application.properties`:

```ini
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 2) Run Backend

```powershell
Set-Location "booking"
.\mvnw.cmd spring-boot:run
```

Backend URL: `http://localhost:8080`

## 3) Run Frontend

Open a new terminal:

```powershell
Set-Location "hotel-frontend"
npm install
npm run dev
```

Frontend URL: `http://localhost:5173`

## 4) Test Flow

1. Open frontend.
2. Search available rooms by date.
3. Click **Book** for a room.
4. Go to **My Bookings**.
5. Click **Cancel** for any booking.

## Main Backend APIs

- `GET /rooms/available?checkIn=YYYY-MM-DD&checkOut=YYYY-MM-DD`
- `POST /bookings`
- `DELETE /bookings/{id}`
- `GET /bookings/user/{userId}`

Sample `POST /bookings` body:

```json
{
  "userId": 1,
  "roomId": 2,
  "checkIn": "2026-06-20",
  "checkOut": "2026-06-22"
}
```

## Notes

- Keep real DB credentials out of GitHub.
- If backend fails with MySQL access denied, verify username/password in `application.properties`.

