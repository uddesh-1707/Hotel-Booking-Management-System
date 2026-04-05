# Hotel Booking Backend (Spring Boot)

Simple Spring Boot Maven project using:
- Spring Web
- Spring Data JPA
- MySQL Driver

## Package Structure

- `com.hotel.booking.controller` - REST APIs (`BookingController`)
- `com.hotel.booking.service` - business logic (`BookingService`, `BookingServiceImpl`)
- `com.hotel.booking.repository` - database access (`BookingRepository`)
- `com.hotel.booking.entity` - JPA entities (`Booking`)

## MySQL Configuration

Main config is in `src/main/resources/application.properties`.
Update these values for your local MySQL:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

## Run

Use Maven wrapper:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Sample API

- `POST /api/bookings`
- `GET /api/bookings`
- `GET /api/bookings/{id}`

