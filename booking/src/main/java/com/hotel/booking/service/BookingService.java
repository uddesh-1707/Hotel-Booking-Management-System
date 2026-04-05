package com.hotel.booking.service;

import com.hotel.booking.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    boolean isRoomAvailable(Long roomId, LocalDate newCheckInDate, LocalDate newCheckOutDate);

    String bookRoom(Booking booking);

    String cancelBooking(Long bookingId);

    List<Booking> getBookingsByUserId(Long userId);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);
}

