package org.logistics.Services.user.servicesImplementation;

import com.google.i18n.phonenumbers.NumberParseException;
import org.logistics.Services.user.userService.UserRegistrationService;
import org.logistics.data.model.User;
import org.logistics.data.model.Wallet;
import org.logistics.data.repository.UserRepository;
import org.logistics.dtos.request.LoginRequest;
import org.logistics.dtos.request.LogoutRequest;
import org.logistics.dtos.request.RegisterRequest;
import org.logistics.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.logistics.validation.Validation.*;
import static org.logistics.utils.Mapper.*;

@Service
public class UserRegistrationServiceImplementation implements UserRegistrationService {
    @Autowired
    UserRepository userRepository;
    public static final long TIMEOUT_DURATION = 3000;
    @Override
    public User register(RegisterRequest registerRequest) throws PhoneNumberValidationException, NumberParseException {
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

        User user = map(firstName,lastName,userName,password,confirmPassword,email,registerRequest.getHomeAddress(),phoneNumber,registerRequest.getRoles());


        Wallet wallet = new Wallet();
        user.setWallet(wallet);

        userRepository.save(user);
        return user;
    }

    @Override
    public void login(LoginRequest loginRequest) {
        cleanupExpiredTokens();

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.findUserByUserName(username);
        validateLogin(username, password);

        if (!usernameExists(username)) {
            throw new LoginCredentialNotValid();
        }

        if (!user.getPassword().equals(password)) {
            throw new LoginCredentialNotValid();
        }

        verifyUserAlreadyLoggedIn(user.isLogin(),user.getLoginToken());
        String loginToken = generateLoginToken();
        user.setLoginToken(loginToken);
        user.setTokenExpiration(generateExpirationTime());


        long timeoutInMillis = user.getTokenExpiration().toEpochMilli() - System.currentTimeMillis();
        if (timeoutInMillis < 0) {
            throw new LoginTimeoutException("Login operation exceeded the timeout of 2 minutes.");
        }

        user.setLogin(true);
        userRepository.save(user);

    }

    @Override
    public User findAccountBelongingTo(String username) {
        User user = userRepository.findUserByUserName(username);

        if (user == null) throw new UserNotFound(username + " not found");

        return user;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {

        String username = logoutRequest.getUsername();
        String password = logoutRequest.getPassword();

        User user = userRepository.findUserByUserName(username);
        validateLogout(username, password);

        if (!usernameExists(username)) {
            throw new LogoutCredentialNotValid();
        }

        if (!user.getPassword().equals(password)) {
            throw new LogoutCredentialNotValid();
        }

        user.setLogin(false);
        userRepository.save(user);
    }


    private boolean usernameExists(String username){
        User user = userRepository.findUserByUserName(username);
        return user != null;
    }

    private String generateLoginToken(){
        return UUID.randomUUID().toString();
    }
    private Instant generateExpirationTime(){
        return Instant.now().plus(Duration.ofMinutes(1));
    }

    @Scheduled(fixedRate = 60000)
    private void cleanupExpiredTokens() {
        userRepository.findAll().forEach(user -> {
            if (user.getTokenExpiration() != null && user.getTokenExpiration().isBefore(Instant.now())) {
                user.setLoginToken(null);
                user.setTokenExpiration(null);
                user.setLogin(false);
                userRepository.save(user);
            }
        });
    }



}
