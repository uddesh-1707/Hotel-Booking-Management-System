package com.hotel.booking.service;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.User;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.booking.repository.RoomRepository;
import com.hotel.booking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate newCheckInDate, LocalDate newCheckOutDate) {
        List<Booking> existingBookings = bookingRepository.findByRoomId(roomId);

        for (Booking existing : existingBookings) {
            if ("CANCELLED".equalsIgnoreCase(existing.getStatus())) {
                continue;
            }

            if (existing.getCheckInDate().isBefore(newCheckOutDate)
                    && existing.getCheckOutDate().isAfter(newCheckInDate)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String bookRoom(Booking booking) {
        if (booking == null || booking.getUser() == null || booking.getRoom() == null) {
            return "Booking must include user and room.";
        }

        Long userId = booking.getUser().getId();
        Long roomId = booking.getRoom().getId();
        if (userId == null || roomId == null) {
            return "User id and room id are required.";
        }

        if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
            return "Check-in and check-out dates are required.";
        }

        if (!booking.getCheckInDate().isBefore(booking.getCheckOutDate())) {
            return "Check-out date must be after check-in date.";
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found.";
        }

        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            return "Room not found.";
        }

        if (!isRoomAvailable(roomId, booking.getCheckInDate(), booking.getCheckOutDate())) {
            return "Room is not available for selected dates.";
        }

        booking.setUser(user);
        booking.setRoom(room);
        if (booking.getStatus() == null || booking.getStatus().trim().isEmpty()) {
            booking.setStatus("CONFIRMED");
        }

        bookingRepository.save(booking);
        return "Booking created successfully.";
    }

    @Override
    public String cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            return "Booking not found.";
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
        return "Booking cancelled successfully.";
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}

