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



}
