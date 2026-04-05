# Hotel Booking Backend (Spring Boot)

Spring Boot backend for a hotel room booking system.

## Tech Stack

- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

## Package Structure

- `com.hotel.booking.controller` - REST APIs
- `com.hotel.booking.service` - booking business logic
- `com.hotel.booking.repository` - JPA repositories
- `com.hotel.booking.entity` - JPA entities (`User`, `Room`, `Booking`)

## Database Configuration

Edit `src/main/resources/application.properties`:

```ini
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

Create DB if missing:

```sql
CREATE DATABASE IF NOT EXISTS hotel_booking_db;
```

## Run

```powershell
.\mvnw.cmd spring-boot:run
```

## Run Tests

```powershell
.\mvnw.cmd test
```

## Main APIs

- `GET /rooms/available?checkIn=YYYY-MM-DD&checkOut=YYYY-MM-DD`
- `POST /bookings`
- `DELETE /bookings/{id}`
- `GET /bookings/user/{userId}`

Sample booking request:

```json
{
  "userId": 1,
  "roomId": 2,
  "checkIn": "2026-06-20",
  "checkOut": "2026-06-22"
}
```

