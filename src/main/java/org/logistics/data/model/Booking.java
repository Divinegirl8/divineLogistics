package org.logistics.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Booking {
    @Id
    private String bookingId;
    private String username;
    private Sender sender;
    private Receiver receiver;
    private boolean isBooked;
    private String parcelDescription;
    private Category category;
    private double parcelWeight;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private boolean isPaid;

    public String toString(){
    return String.format("""
            Booking has been made successfully,below is your booking details...
            BOOKING TICKET ID: %s
            SENDER INFORMATION:\s
            Sender Name: %s %s
            Sender Email Address: %s
            Sender Phone Number: %s
            SENDER ADDRESS:\s
            Country: %s
            State: %s
            House Number: %s
            Street Name: %s
            Postal Code: %s
            
            RECEIVER INFORMATION:\s
            Receiver Name: %s %s
            Receiver Email Address: %s
            Receiver Phone Number : %s
            RECEIVER ADDRESS:\s
            Country: %s
            State: %s
            House Number: %s
            Street Name: %s
            Postal Code: %s
            
            Parcel Description: %s
            Category: %s
            Parcel Weight: %.1f
            Amount: %s naira
            Time Booked: %s
            """

            ,bookingId,sender.getFirstName(),sender.getLastName(),sender.getSenderEmailAddress(),sender.getPhoneNumber(),sender.getHomeAddress().getCountry(),sender.getHomeAddress().getState(),sender.getHomeAddress().getHouseNumber(),
            sender.getHomeAddress().getStreetName(),sender.getHomeAddress().getPostalCode(),receiver.getFirstName(),receiver.getLastName(),receiver.getEmailAddress(),receiver.getPhoneNumber(),
    receiver.getHomeAddress().getCountry(),receiver.getHomeAddress().getState(),receiver.getHomeAddress().getHouseNumber(),receiver.getHomeAddress().getStreetName(),receiver.getHomeAddress().getPostalCode(),
            parcelDescription,category,parcelWeight,amount,dateTime

    );}



}
