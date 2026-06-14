package com.koushik.bookingservice.service.impl;

import com.koushik.bookingservice.domain.BookingStatus;
import com.koushik.bookingservice.model.Booking;
import com.koushik.bookingservice.model.SalonReport;
import com.koushik.bookingservice.payload.dto.BookingRequest;
import com.koushik.bookingservice.payload.dto.SalonDto;
import com.koushik.bookingservice.payload.dto.ServiceOfferingDto;
import com.koushik.bookingservice.payload.dto.UserDto;
import com.koushik.bookingservice.repository.BookingRepository;
import com.koushik.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest bookingRequest, UserDto userDto, SalonDto salonDto, Set<ServiceOfferingDto> serviceDtoSet) throws Exception {

        int totalDuration = serviceDtoSet.stream().mapToInt(ServiceOfferingDto::getDuration).sum();

        LocalDateTime bookingStartTime = bookingRequest.getStartTime(); //from frontend
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable =  this.isTimeSlotAvailable(salonDto, bookingStartTime, bookingEndTime);

        int totalPrice = serviceDtoSet.stream().mapToInt(ServiceOfferingDto::getPrice).sum();

        Set<Long> idList = serviceDtoSet.stream().map(ServiceOfferingDto::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDto.getId());
        newBooking.setSalonId(salonDto.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDto, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime) throws Exception {

        //salon open and close time - converting LocalTime to LocalDatetime
        LocalDateTime salonOpenTime = salonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime =  salonDto.getCloseTime().atDate(bookingEndTime.toLocalDate());

        List<Booking> existingBookings = getBookingsBySalon(salonDto.getId());

        if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time must be within salon's working hours");
        }

        //checking if that slot has some other booking
        for(Booking existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("Slot not available, please choose different time");
            }

            if(bookingStartTime.equals(existingBookingStartTime) && bookingEndTime.equals(existingBookingEndTime)) {
                throw new Exception("Slot not available, please choose different time");
            }
        }

        return true;

    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if(booking == null) {
            throw new Exception("Booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {

        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        List<Booking> allBookings = getBookingsBySalon(salonId);
        if(date == null) {
            return allBookings;
        }

        return allBookings.stream().filter(
                booking -> isSameDate(booking.getStartTime(),date) || isSameDate(booking.getEndTime(),date))
        .toList();

    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {

        List<Booking> bookings = getBookingsBySalon(salonId);

        int totalEarnings = bookings.stream().mapToInt(Booking::getTotalPrice).sum();

        Integer totalBookings = bookings.size();

        List<Booking> cancelledBookings = bookings.stream().filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED)).toList();

        Double totalRefunds = cancelledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        SalonReport salonReport = new SalonReport();
        salonReport.setSalonId(salonId);
        salonReport.setCancelledBookings(cancelledBookings.size());
        salonReport.setTotalBookings(totalEarnings);
        salonReport.setTotalEarnings(totalEarnings);
        salonReport.setTotalRefund(totalRefunds);
        salonReport.setTotalBookings(totalBookings);

        return salonReport;
    }
}
