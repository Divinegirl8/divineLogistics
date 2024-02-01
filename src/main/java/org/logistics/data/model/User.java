package org.logistics.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String emailAddress;
    private Address homeAddress;
    private boolean isLogin;
    private String loginToken;
    private Instant tokenExpiration;
    private Wallet wallet;
    private String phoneNumber;
    private Roles roles;
}
