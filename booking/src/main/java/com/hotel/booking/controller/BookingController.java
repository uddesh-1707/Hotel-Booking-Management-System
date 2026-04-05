package com.hotel.booking.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.User;
import com.hotel.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> bookRoom(@RequestBody BookingRequest request) {
        Booking booking = new Booking();
        User user = new User();
        user.setId(request.getUserId());
        Room room = new Room();
        room.setId(request.getRoomId());

        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setStatus(request.getStatus());

        String result = bookingService.bookRoom(booking);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);

        if ("Booking created successfully.".equals(result)) {
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> cancelBooking(@PathVariable Long id) {
        String result = bookingService.cancelBooking(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);

        if ("Booking not found.".equals(result)) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    public static class BookingRequest {
        private Long userId;
        private Long roomId;
        @JsonAlias("checkIn")
        private LocalDate checkInDate;
        @JsonAlias("checkOut")
        private LocalDate checkOutDate;
        private String status;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getRoomId() {
            return roomId;
        }

        public void setRoomId(Long roomId) {
            this.roomId = roomId;
        }

        public LocalDate getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
        }

        public LocalDate getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

