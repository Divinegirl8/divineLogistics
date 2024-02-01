package org.logistics.Services.admin.serviceImplemntation;

import com.google.i18n.phonenumbers.NumberParseException;
import org.logistics.Services.admin.adminService.AdminServiceRegistration;
import org.logistics.data.model.Admin;
import org.logistics.data.model.User;
import org.logistics.data.model.Wallet;
import org.logistics.data.repository.AdminRepository;
import org.logistics.dtos.request.LoginRequest;
import org.logistics.dtos.request.LogoutRequest;
import org.logistics.dtos.request.RegisterRequest;
import org.logistics.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.logistics.utils.Mapper.map;
import static org.logistics.utils.Mapper.mapAdmin;
import static org.logistics.validation.Validation.*;
import static org.logistics.validation.Validation.validateRoles;

@Service
public class AdminServiceRegistrationImplementation implements AdminServiceRegistration {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin registerAdmin(RegisterRequest registerRequest) throws PhoneNumberValidationException, NumberParseException {
        validateAddress(registerRequest.getHomeAddress());
        validateFirstName(registerRequest.getFirstName());
        String firstName = registerRequest.getFirstName().trim();

        validateLastName(registerRequest.getLastName());
        String lastName = registerRequest.getLastName().trim();

        validateUserName(registerRequest.getUserName());
        if (usernameExists(registerRequest.getUserName())){
            throw new UserNameAlreadyExists(registerRequest.getUserName()+ " already exists");
        }
        String userName = registerRequest.getUserName().trim();

        validatePassword(registerRequest.getPassword());
        String password = registerRequest.getPassword().trim();

        validateConfirmPassword(registerRequest.getPassword(),registerRequest.getConfirmPassword());
        String confirmPassword = registerRequest.getConfirmPassword().trim();

        validateEmail(registerRequest.getEmailAddress());
        String email = registerRequest.getEmailAddress().trim();

        validateCountry(registerRequest.getHomeAddress());

        validatePhoneNumber(registerRequest.getPhoneNumber());
        String phoneNumber = registerRequest.getPhoneNumber().trim();

        validateRoles(registerRequest.getRoles());

        Admin admin = mapAdmin(firstName,lastName,userName,password,confirmPassword,email,registerRequest.getHomeAddress(),phoneNumber,registerRequest.getRoles());

        adminRepository.save(admin);
        return admin;
    }

    @Override
    public void login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Admin admin = adminRepository.findUserByUserName(username);
        validateLogin(username, password);

        if (!usernameExists(username)) {
            throw new LoginCredentialNotValid();
        }

        if (!admin.getPassword().equals(password)) {
            throw new LoginCredentialNotValid();
        }

        admin.setLogin(true);
        adminRepository.save(admin);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        String username = logoutRequest.getUsername();
        String password = logoutRequest.getPassword();

        Admin admin = adminRepository.findUserByUserName(username);
        validateLogin(username, password);

        if (!usernameExists(username)) {
            throw new LogoutCredentialNotValid();
        }

        if (!admin.getPassword().equals(password)) {
            throw new LogoutCredentialNotValid();
        }

        admin.setLogin(false);
        adminRepository.save(admin);

    }

    private boolean usernameExists(String username){
        Admin admin = adminRepository.findUserByUserName(username);
        return admin != null;
    }

}
