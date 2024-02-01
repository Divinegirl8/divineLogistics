package org.logistics.ServicesImplementation;

import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.logistics.Services.user.userService.UserRegistrationService;
import org.logistics.data.model.Address;
import org.logistics.data.model.Roles;
import org.logistics.data.repository.UserRepository;
import org.logistics.dtos.request.LoginRequest;
import org.logistics.dtos.request.LogoutRequest;
import org.logistics.dtos.request.RegisterRequest;
import org.logistics.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRegistrationServiceImplementationTest {
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    UserRepository userRepository;



    @AfterEach
    void delete_Information_In_DataBase(){
        userRepository.deleteAll();
    }

    @Test
    void register_User() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","Username_4","Password6#","Password6#","email2@gmail.com",address,"09062346551",Roles.USER);
        System.out.println(userRegistrationService.register(registerRequest));
        assertEquals(1,userRepository.count());
    }
    @Test
    void register_User_With_Same_Username() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","Username_4","Password6#","Password6#","email2@gmail.com",address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);
        assertThrows(UserNameAlreadyExists.class,()-> userRegistrationService.register(registerRequest));
    }
    @Test
    void register_User_with_Wrong_First_Name(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName2!","lastName","username_4","  Password6#","Password6#","email",address,"09062346551",Roles.USER);
        assertThrows(FirstNameContainsNumberError.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_Without_First_Name(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("","lastName","username","password","password","email",address,"09062346551",Roles.USER);
        assertThrows(FirstNameFieldIsEmpty.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_with_Wrong_Last_Name(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","last56Name%","username","password","password","email",address,"09062346551",Roles.USER);
        assertThrows(LastNameContainsNumberError.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_Without_Last_Name(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","","username","password","password","email",address,"09062346551",Roles.USER);
        assertThrows(LastNameFieldIsEmpty.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_With_Wrong_UserName(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","2&username","password","password","email",address,"09062346551",Roles.USER);
        assertThrows(UserNameNotValid.class,()-> userRegistrationService.register(registerRequest));
    }
    @Test
    void register_User_With_Wrong_UserName2(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","Use_","password","password","email",address,"09062346551",Roles.USER);
        assertThrows(UserNameNotValid.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_With_Wrong_Password(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","User_5","password","password","email",address,"09062346551",Roles.USER);
        assertThrows(PasswordNotValid.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_With_Wrong_Confirm_Password(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","User_5","Password8_","Password","email",address,"09062346551",Roles.USER);
        assertThrows(PasswordNotValid.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test
    void register_User_With_Wrong_Email(){
        Address address = address("Nigeria","Lagos","23","Wellington","12345");
        RegisterRequest registerRequest = register_Request("firstName","lastName","Username_4","Password6#","Password6#","email2gmail.com",address,"09062346551",Roles.USER);
        assertThrows(EmailNotValid.class,()-> userRegistrationService.register(registerRequest));
    }

    @Test()
    void register_User_Login_With_Right_Credentials() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);
        assertTrue(userRegistrationService.findAccountBelongingTo("Username_4").isLogin());


    }
    @Test
    void register_User_Login_With_Wrong_Username() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username", "Password6#");
        assertThrows(LoginCredentialNotValid.class,()-> userRegistrationService.login(loginRequest));

    }

    @Test()
    void register_User_Login_With_Wrong_Password() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username_4", "Password");

        assertThrows(LoginCredentialNotValid.class,()-> userRegistrationService.login(loginRequest));

    }

    @Test()
    void register_User_Login_With_Right_Credentials_Login_Again() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);
       assertThrows(UserAlreadyLoggedInException.class,()-> userRegistrationService.login(loginRequest));

    }


    @Test()
    void register_User_Login_With_Right_Credentials_Logout_With_Right_Credentials() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);
        assertTrue(userRegistrationService.findAccountBelongingTo("Username_4").isLogin());


         LogoutRequest logoutRequest = logoutRequest("Username_4","Password6#");
        userRegistrationService.logout(logoutRequest);
        assertFalse(userRegistrationService.findAccountBelongingTo("Username_4").isLogin());


    }


    @Test()
    void register_User_Login_With_Right_Credentials_Logout_With_Wrong_Username() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);
        assertTrue(userRegistrationService.findAccountBelongingTo("Username_4").isLogin());


        LogoutRequest logoutRequest = logoutRequest("Username","Password6#");
        assertThrows(LogoutCredentialNotValid.class,()-> userRegistrationService.logout(logoutRequest));

    }

    @Test()
    void register_User_Login_With_Right_Credentials_Logout_With_Wrong_Password() throws PhoneNumberValidationException, NumberParseException {
        Address address = address("Nigeria", "Lagos", "23", "Wellington", "12345");

        RegisterRequest registerRequest = register_Request("firstName", "lastName", "Username_4", "Password6#", "Password6#", "email2@gmail.com", address,"09062346551",Roles.USER);
        userRegistrationService.register(registerRequest);

        LoginRequest loginRequest = loginRequest("Username_4", "Password6#");
        userRegistrationService.login(loginRequest);
        assertTrue(userRegistrationService.findAccountBelongingTo("Username_4").isLogin());


        LogoutRequest logoutRequest = logoutRequest("Username_4","Password");
        assertThrows(LogoutCredentialNotValid.class,()-> userRegistrationService.logout(logoutRequest));

    }





    public Address address(String country, String state,String houseNumber,String streetName,String postalCode){
        Address address = new Address();
        address.setCountry(country);
        address.setState(state);
        address.setHouseNumber(houseNumber);
        address.setStreetName(streetName);
        address.setPostalCode(postalCode);

        return address;
    }
    public RegisterRequest register_Request(String firstName, String lastName, String userName, String password, String confirmPassword, String emailAddress, Address homeAddress, String phoneNumber, Roles roles){
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
    public LoginRequest loginRequest(String username,String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginRequest;
    }

    public LogoutRequest logoutRequest(String username,String password){
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername(username);
        logoutRequest.setPassword(password);

        return logoutRequest;
    }

}