package com.koushik.bookingservice.service;

import com.koushik.bookingservice.domain.BookingStatus;
import com.koushik.bookingservice.model.Booking;
import com.koushik.bookingservice.model.SalonReport;
import com.koushik.bookingservice.payload.dto.BookingRequest;
import com.koushik.bookingservice.payload.dto.SalonDto;
import com.koushik.bookingservice.payload.dto.ServiceOfferingDto;
import com.koushik.bookingservice.payload.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest, UserDto userDto, SalonDto salonDto, Set<ServiceOfferingDto> serviceDtoSet) throws Exception;
    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
