package org.logistics.dtos.request;

import lombok.Data;
import org.logistics.data.model.Address;
import org.logistics.data.model.Roles;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String emailAddress;
    private Address homeAddress;
    private String phoneNumber;
    private Roles roles;

}
