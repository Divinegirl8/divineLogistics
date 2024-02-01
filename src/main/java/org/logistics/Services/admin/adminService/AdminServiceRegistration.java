package org.logistics.Services.admin.adminService;

import com.google.i18n.phonenumbers.NumberParseException;
import org.logistics.data.model.Admin;
import org.logistics.dtos.request.LoginRequest;
import org.logistics.dtos.request.LogoutRequest;
import org.logistics.dtos.request.RegisterRequest;
import org.logistics.exceptions.PhoneNumberValidationException;

public interface AdminServiceRegistration {
    Admin registerAdmin(RegisterRequest registerRequest) throws PhoneNumberValidationException, NumberParseException;
    void login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
}
