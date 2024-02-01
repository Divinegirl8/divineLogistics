package org.logistics.data.model;

import lombok.Data;

@Data
public class Sender {
    private String firstName;
    private String lastName;
    private String senderEmailAddress;
    private String phoneNumber;
    private Address homeAddress;
}
