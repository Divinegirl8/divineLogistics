package org.logistics.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Receiver {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
}
