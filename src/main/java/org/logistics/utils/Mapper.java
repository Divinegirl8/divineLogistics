package org.logistics.utils;

import org.logistics.data.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Mapper {
    public static User map(String firstName, String lastName, String userName, String password, String confirmPassword,String emailAddress, Address homeAddress,String phoneNumber,Roles roles){
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        user.setEmailAddress(emailAddress);
        user.setHomeAddress(homeAddress);
        user.setPhoneNumber(phoneNumber);
        user.setRoles(roles);

        return user;
    }

    public static Receiver mapReceiver(String firstName,String lastName,String phoneNumber,String emailAddress){
        Receiver receiver = new Receiver();
        receiver.setFirstName(firstName);
        receiver.setLastName(lastName);
        receiver.setPhoneNumber(phoneNumber);
        receiver.setEmailAddress(emailAddress);

        return receiver;
    }

    public static Booking mapBook(String bookingId, Sender sender, Receiver receiver, Category category, String parcelDescription, double parcelWeight, BigDecimal amount,LocalDateTime dateTime,String username){
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setSender(sender);
        booking.setReceiver(mapReceiver(receiver.getFirstName(),receiver.getLastName(),receiver.getPhoneNumber(),receiver.getEmailAddress()));
        booking.setCategory(category);
        booking.setParcelDescription(parcelDescription);
        booking.setParcelWeight(parcelWeight);
        booking.setAmount(amount);
        booking.setDateTime(dateTime);
        booking.setUsername(username);


        return booking;
    }

    public static Sender mapSender(String firstName,String lastName,String emailAddress,String phoneNumber,Address homeAddress){
        Sender sender = new Sender();
        sender.setFirstName(firstName);
        sender.setLastName(lastName);
        sender.setSenderEmailAddress(emailAddress);
        sender.setPhoneNumber(phoneNumber);
        sender.setHomeAddress(homeAddress);

        return sender;
    }

    public static Admin mapAdmin(String firstName, String lastName, String userName, String password, String confirmPassword,String emailAddress, Address homeAddress,String phoneNumber,Roles roles){
        Admin admin = new Admin();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setUserName(userName);
        admin.setPassword(password);
        admin.setConfirmPassword(confirmPassword);
        admin.setEmailAddress(emailAddress);
        admin.setHomeAddress(homeAddress);
        admin.setPhoneNumber(phoneNumber);
        admin.setRoles(roles);
        return admin;
    }

}
