package org.logistics.dtos.request;

import lombok.Data;
import org.logistics.data.model.Category;
import org.logistics.data.model.Receiver;

import java.math.BigDecimal;

@Data
public class BookingRequest {
    private String username;
    private Receiver receiver;
    private String parcelDescription;
    private Category category;
    private double parcelWeight;

}
