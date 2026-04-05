# Hotel Booking Frontend (React)

React frontend for the hotel booking system.

## Tech Stack

- React
- Vite
- Axios

## Features

- Search available rooms by check-in/check-out dates
- Book a room (uses user id `1` for demo)
- View bookings for user `1`
- Cancel booking

## Run

```powershell
npm install
npm run dev
```

Frontend runs on `http://localhost:5173`.

## Build

```powershell
npm run build
```

## Backend Requirement

Backend should be running on `http://localhost:8080`.
Vite proxy is configured for `/rooms` and `/bookings` in `vite.config.js`.
