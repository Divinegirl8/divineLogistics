package org.logistics.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonemetadata;
import org.logistics.data.model.*;
import org.logistics.dtos.request.BookingRequest;
import org.logistics.exceptions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.security.auth.login.LoginException;

public class Validation {

    public static void validateFirstName(String firstName){
        if (firstName == null || firstName.trim().isEmpty()){
            throw new FirstNameFieldIsEmpty("first name field is empty, kindly provide your first name");
        }
        if (!firstName.matches("[a-zA-z]+")) {
            throw new FirstNameContainsNumberError("please note that first name should contain letters only");
        }

    }


    public static void validateLastName(String lastName){
        if (lastName == null || lastName.trim().isEmpty()){
            throw new LastNameFieldIsEmpty("last name field is empty, kindly provide your last name");
        }
        if (!lastName.matches("[a-zA-z]+")) {
            throw new LastNameContainsNumberError("please note that last name should contain letters only");
        }

    }

    public static void validateUserName(String username){
        if (username == null || username.trim().isEmpty()){
            throw new UserNameFieldIsEmpty("username field is empty, kindly provide your username");
        }

        if (!username.matches("^[a-zA-Z].*[0-9]+.*[!@#$%^&*()]*$") || username.length() < 4){
            throw new UserNameNotValid("username must start with a letter and must contain numbers and special characters\nThe length must be greater than four");
        }
    }

    public static void  validatePassword(String password){
        if (password == null|| password.trim().isEmpty()){
            throw new PasswordFieldIsEmpty("password field is empty, kindly provide your password");
        }

        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()])(?!.*\\s).{6,}$")) {
            throw new PasswordNotValid("Password is too weak. It must contain letters, numbers, and special characters. The length must be greater than 5.");
        }

    }


        public static void validateConfirmPassword(String password, String confirmPassword) {
            if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
                throw new ConfirmPasswordFieldIsEmpty("confirm password field is empty, kindly provide your confirm password");
            }

            if (!password.equals(confirmPassword)) {
                throw new ConfirmPasswordNotMatch("confirm password does not match the password");
            }
        }

        public static void validateEmail(String email){
        if (email == null || email.trim().isEmpty()) {
            throw new EmailFieldIsEmpty("email field is empty, kindly provide your email");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}$")){
            throw new EmailNotValid("email address is not valid");
        }
        }

        public static void  validateCountry(Address address){
            if (address.getCountry() == null || address.getCountry().trim().isEmpty()) {
                throw new CountryFieldIsEmpty("country field is empty, kindly provide your country");
            }
            if (address.getState() == null || address.getState().trim().isEmpty()) {
                throw new StateFieldIsEmpty("state field is empty, kindly provide your state");
            }
            if (address.getHouseNumber() == null || address.getHouseNumber().trim().isEmpty()) {
                throw new HouseNumberFieldIsEmpty("house number field is empty, kindly provide your house number");
            }
            if (address.getPostalCode() == null || address.getPostalCode().trim().isEmpty()) {
                throw new PostalCodeIsEmpty("postal code field is empty, kindly provide your postal code");
            }
            if (address.getStreetName() == null || address.getStreetName().trim().isEmpty()) {
                throw new StreetNameFieldIsEmpty("street name field is empty, kindly provide your street name");
            }

        }

        public static void validateLogin(String username,String password){
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()){
            throw new LoginFieldIsEmpty("login field should not be empty");
        }
        }

    public static void validateLogout(String username,String password){
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()){
            throw new LogoutFieldIsEmpty("logout field should not be empty");
        }
    }

      public static void verifyUserAlreadyLoggedIn(boolean isLogin, String loginToken){
        if (isLogin || loginToken != null){
            throw new UserAlreadyLoggedInException();
        }
      }

      public static void validateBooking(BookingRequest bookingRequest) throws PhoneNumberValidationException, NumberParseException {
        if (bookingRequest.getParcelWeight() == 0 || String.valueOf(bookingRequest.getParcelWeight()).trim().isEmpty()) {
            throw new ParcelWeightIsNotValid("parcel weight cannot be empty and cannot be 0");
        }

        if (bookingRequest.getUsername() == null || bookingRequest.getUsername().trim().isEmpty()){
            throw new BookingRequestFieldError("Username field cannot be empty");
        }

        if (bookingRequest.getParcelDescription() == null || bookingRequest.getParcelDescription().trim().isEmpty()){
            throw new BookingRequestFieldError("Parcel description field cannot be empty");
        }
        validateReceiver(bookingRequest.getReceiver());

      }

      public static void validateAmountField(BigDecimal amount){
        if (amount == null ||String.valueOf(amount).trim().isEmpty()){
            throw new AmountFieldMustNotBeEmpty("field must not be empty");
        }
      }

    public static void validateDepositAmount(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidDepositAmountException("Error!!!, the amount you are trying to deposit must be greater than 0 \nTry again");
    }

    public static void validateSufficientFund(BigDecimal balance,BigDecimal amount){
        if (balance == null || amount == null || balance.compareTo(amount)  < 0) throw new InsufficientFundsError("Error!!! your wallet balance is lower than the money you are trying to withdraw");
    }

    public static void validateWithDraw(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)  throw new LowAmountError("Error!!! The amount you are trying to withdraw must be greater than 0");
    }

    public static void validatePhoneNumber(String phoneNumber) throws PhoneNumberValidationException, NumberParseException {

        if (phoneNumber == null || phoneNumber.trim().isEmpty()){
            throw new PhoneNumberFieldCannotBeEmpty("phone number field cannot be empty");
        }
        phoneNUmberValidation(phoneNumber);
    }

    public static void validateReceiver(Receiver receiver) throws PhoneNumberValidationException, NumberParseException {
  validateFirstName(receiver.getFirstName());
  validateLastName(receiver.getLastName());
  validatePhoneNumber(receiver.getPhoneNumber());
  validateEmail(receiver.getEmailAddress());

    }

    public static void validateCategory(Category category){

        if (category == null){
            throw new categoryException("Category field cannot be empty");
        }

    }

    public static void validateRoles(Roles roles){

        if (roles == null){
            throw new categoryException("Roles field cannot be empty");
        }

    }

    public static void validateUserRoles(Roles roles){
        if (roles != Roles.USER){
            throw new RolesException("you cannot perform this operation because you are not a user");
        }
    }

    public static void validateLogin(User user){
        if (!user.isLogin()){
            throw new NotLoginException("you must login to perform this action");
        }
    }
    public static void validateAddress(Address address){
        if (address == null){
            throw new AddressFieldCannotBeEmpty("Address field cannot be empty");
        }
    }



    public static boolean isPhoneNumberValid(String phone) throws InvalidPhoneNumberException, NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phone, "IN");

        return phoneUtil.isValidNumber(phoneNumber);
    }

    public static void phoneNUmberValidation(String number) throws NumberParseException, PhoneNumberValidationException {
        if (!isPhoneNumberValid(number)){
            throw new PhoneNumberValidationException("invalid phone number");
        }
    }




    }











