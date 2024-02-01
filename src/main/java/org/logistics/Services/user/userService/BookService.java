package org.logistics.Services.user.userService;

import com.google.i18n.phonenumbers.NumberParseException;
import org.logistics.data.model.Booking;
import org.logistics.dtos.request.BookingRequest;
import org.logistics.exceptions.PhoneNumberValidationException;

public interface BookService {
    Booking book(BookingRequest bookingRequest) throws PhoneNumberValidationException, NumberParseException;
    void payBookingCost(String username);
}
