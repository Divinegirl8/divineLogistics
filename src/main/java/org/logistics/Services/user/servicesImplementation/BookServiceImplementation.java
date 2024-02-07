package org.logistics.Services.user.servicesImplementation;

import com.google.i18n.phonenumbers.NumberParseException;
import org.logistics.Services.email.EmailService;
import org.logistics.Services.user.userService.BookService;
import org.logistics.Services.user.userService.WalletService;
import org.logistics.data.model.*;
import org.logistics.data.repository.BookingRepository;
import org.logistics.data.repository.UserRepository;
import org.logistics.dtos.request.BookingRequest;
import org.logistics.exceptions.BookingNotFound;
import org.logistics.exceptions.PhoneNumberValidationException;
import org.logistics.exceptions.ReceiverInformationException;
import org.logistics.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.logistics.validation.Validation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.logistics.utils.Mapper.*;

@Service
public class BookServiceImplementation implements BookService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WalletService walletService;
    @Autowired
    EmailService emailService;
    private static final BigDecimal FIXED_BOOKING_AMOUNT = BigDecimal.valueOf(2500);
    @Override
    public Booking book(BookingRequest bookingRequest) throws PhoneNumberValidationException, NumberParseException {
        if (bookingRequest.getReceiver() == null) {
            throw new ReceiverInformationException("Receiver information cannot be null");
        }

       validateCategory(bookingRequest.getCategory());
        Receiver receiver = mapReceiver(bookingRequest.getReceiver().getFirstName(),bookingRequest.getReceiver().getLastName(),bookingRequest.getReceiver().getPhoneNumber(),bookingRequest.getReceiver().getEmailAddress(),bookingRequest.getReceiver().getHomeAddress());
        bookingRequest.setReceiver(receiver);


        Booking booking = new Booking();

        validateBooking(bookingRequest);
        validateReceiver(receiver);



        if (bookingRequest.getParcelWeight() < 1.0){
            booking.setAmount(FIXED_BOOKING_AMOUNT);
        }else {
            booking.setAmount(FIXED_BOOKING_AMOUNT.multiply(BigDecimal.valueOf(bookingRequest.getParcelWeight())));
        }

        User user = userRepository.findUserByUserName(bookingRequest.getUsername());
        if (user == null) throw new UserNotFound(bookingRequest.getUsername() + " not found");

       validateUserRoles(user.getRoles());
       validateLogin(user);
        Sender sender = mapSender(user.getFirstName(),  user.getLastName(), user.getEmailAddress(),user.getPhoneNumber(),user.getHomeAddress());
        booking.setSender(sender);

        Booking book = mapBook("BID"+(bookingRepository.count()+1),sender,bookingRequest.getReceiver(),bookingRequest.getCategory(),bookingRequest.getParcelDescription(),bookingRequest.getParcelWeight(),booking.getAmount(), LocalDateTime.now(), user.getUserName());
        emailService.sendEmail(booking.getSender().getSenderEmailAddress(),"Parcel Booking Payment","The price for the booking is " + booking.getAmount() + " naira, kindly make payment to complete your booking.");
        bookingRepository.save(book);

        return book;
    }

    @Override
    public void payBookingCost(String username) {
        User user = userRepository.findUserByUserName(username);
        if (user == null) throw new UserNotFound(username + " not found");

        validateUserRoles(user.getRoles());

        List<Booking> bookings = bookingRepository.findByUsername(username);
        if (bookings.isEmpty()) {
            throw new BookingNotFound("No bookings found for user: " + username);
        }

      BigDecimal amount = addAmount(bookings);


        walletService.deductMoney(username,amount);

        for (Booking booking : bookings) {
            booking.setBooked(true);
            booking.setPaid(true);

            emailService.sendEmail(booking.getSender().getSenderEmailAddress(), "Parcel Booking Successful", String.valueOf(booking));
            bookingRepository.save(booking);
        }
    }

}
