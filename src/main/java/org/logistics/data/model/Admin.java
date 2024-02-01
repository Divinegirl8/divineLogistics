package org.logistics.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Admin {
    @Id
    private String adminId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String emailAddress;
    private Address homeAddress;
    private boolean isLogin;
    private String phoneNumber;
    private Roles roles;
}
