package org.logistics.Services.user.userService;

import com.google.i18n.phonenumbers.NumberParseException;
import org.logistics.data.model.User;
import org.logistics.dtos.request.LoginRequest;
import org.logistics.dtos.request.LogoutRequest;
import org.logistics.dtos.request.RegisterRequest;
import org.logistics.exceptions.PhoneNumberValidationException;

public interface UserRegistrationService {
    User register(RegisterRequest registerRequest) throws PhoneNumberValidationException, NumberParseException;
    void login(LoginRequest loginRequest);
    User findAccountBelongingTo(String username);
    void logout(LogoutRequest logoutRequest);
}
