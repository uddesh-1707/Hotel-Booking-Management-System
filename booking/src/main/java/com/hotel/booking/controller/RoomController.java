package com.hotel.booking.controller;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.booking.repository.RoomRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomController(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {

        List<Room> rooms = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            boolean available = true;
            List<Booking> bookings = bookingRepository.findByRoomId(room.getId());

            for (Booking existing : bookings) {
                if ("CANCELLED".equalsIgnoreCase(existing.getStatus())) {
                    continue;
                }

                if (existing.getCheckInDate().isBefore(checkOut)
                        && existing.getCheckOutDate().isAfter(checkIn)) {
                    available = false;
                    break;
                }
            }

            if (available) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
}

