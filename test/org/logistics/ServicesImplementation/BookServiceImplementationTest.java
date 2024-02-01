package org.logistics.ServicesImplementation;

import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.logistics.Services.user.userService.BookService;
import org.logistics.Services.user.userService.UserRegistrationService;
import org.logistics.Services.user.userService.WalletService;
import org.logistics.data.model.*;
import org.logistics.data.repository.BookingRepository;
import org.logistics.data.repository.UserRepository;
import org.logistics.dtos.request.BookingRequest;
import org.logistics.dtos.request.LoginRequest;
import org.logistics.dtos.request.LogoutRequest;
import org.logistics.dtos.request.RegisterRequest;
import org.logistics.exceptions.PhoneNumberValidationException;
import org.logistics.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplementationTest {
 @Autowired
 UserRegistrationService userRegistrationService;
 @Autowired
    BookService bookService;
 @Autowired
    UserRepository userRepository;
 @Autowired
    BookingRepository bookingRepository;
@Autowired
    WalletService walletService;
 @AfterEach
    void cleanUp(){
     userRepository.deleteAll();
     bookingRepository.deleteAll();
 }
@Test void register_User_Login_Book_Service() throws PhoneNumberValidationException, NumberParseException {
    Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

    RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
    userRegistrationService.register(registerRequest);

    LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
    userRegistrationService.login(loginRequest);

    Receiver receiver = receiver("Sharon","Jones","09062346551","iam2@gmail.com");

 BookingRequest bookingRequest =   bookingRequest("Username_4",receiver,"dress",Category.CLOTHING,10);
    bookService.book(bookingRequest);



    assertEquals(1,bookingRepository.count());

}
    @Test void register_User_Login_Book_Service_Pay_For_Bookings() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);


        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);

        Receiver receiver = receiver("Sharon","Jones","09062346551","iam2@gmail.com");

        BookingRequest bookingRequest = bookingRequest("Username_4",receiver,"dress",Category.CLOTHING,10);
        Booking booking1 = bookService.book(bookingRequest);
        assertFalse(booking1.isBooked());


        assertEquals(1,bookingRepository.count());

        walletService.addMoney("Username_4", BigDecimal.valueOf(100_000));


        bookService.payBookingCost("Username_4");

        Booking booking = bookingRepository.findByUsername("Username_4");
        assertTrue(booking.isBooked());
        assertTrue(booking.isPaid());

    }


    @Test void register_User_Login_Book_Service_With_Wrong_Username() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09071272729",Roles.USER);
        userRegistrationService.register(registerRequest);


        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);

        Receiver receiver = receiver("Sharon","Jones","09062346551","iam2@gmail.com");

        BookingRequest bookingRequest = bookingRequest("Username",receiver,"dress",Category.CLOTHING,10);



        assertThrows(UserNotFound.class,()-> bookService.book(bookingRequest));
    }
    public Address address(String country, String state, String houseNumber, String streetName, String postalCode){
        Address address = new Address();
        address.setCountry(country);
        address.setState(state);
        address.setHouseNumber(houseNumber);
        address.setStreetName(streetName);
        address.setPostalCode(postalCode);

        return address;
    }
    public RegisterRequest register_Request(String firstName, String lastName, String userName, String password, String confirmPassword, String emailAddress, Address homeAddress,String phoneNumber,Roles roles){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName(firstName);
        registerRequest.setLastName(lastName);
        registerRequest.setUserName(userName);
        registerRequest.setPassword(password);
        registerRequest.setConfirmPassword(confirmPassword);
        registerRequest.setEmailAddress(emailAddress);
        registerRequest.setHomeAddress(homeAddress);
       registerRequest.setPhoneNumber(phoneNumber);
       registerRequest.setRoles(roles);
        return registerRequest;

    }
    public LoginRequest loginRequest(String username, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginRequest;
    }

    public LogoutRequest logoutRequest(String username, String password){
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername(username);
        logoutRequest.setPassword(password);

        return logoutRequest;
    }
    public BookingRequest bookingRequest(String username, Receiver receiver, String parcelDescription, Category category,double parcelWeight){
     BookingRequest bookingRequest = new BookingRequest();
     bookingRequest.setUsername(username);
     bookingRequest.setReceiver(receiver);
     bookingRequest.setParcelDescription(parcelDescription);
    bookingRequest.setCategory(category);
     bookingRequest.setParcelWeight(parcelWeight);

     return bookingRequest;
    }

    public Receiver receiver(String firstName, String lastName,String phoneNumber,String emailAddress){
     Receiver receiver = new Receiver();
     receiver.setFirstName(firstName);
     receiver.setLastName(lastName);
     receiver.setPhoneNumber(phoneNumber);
     receiver.setEmailAddress(emailAddress);

return receiver;
    }

}