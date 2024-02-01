package org.logistics.Services.user.servicesImplementation;

import org.logistics.Services.user.userService.WalletService;
import org.logistics.data.model.User;
import org.logistics.data.model.Wallet;
import org.logistics.data.repository.UserRepository;
import org.logistics.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.logistics.validation.Validation.*;

import java.math.BigDecimal;

@Service
public class WalletServiceImplementation implements WalletService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void addMoney(String username,BigDecimal amount) {
        User user = userRepository.findUserByUserName(username);

        if (user == null) throw new UserNotFound(username + " not found");

        validateUserName(username);
        validateAmountField(amount);
        validateDepositAmount(amount);

        Wallet wallet = user.getWallet();
        BigDecimal balance = wallet.getBalance();

         wallet.setBalance(balance.add(amount));
         userRepository.save(user);

    }

    @Override
    public void deductMoney(String username, BigDecimal amount) {

        User user = userRepository.findUserByUserName(username);

        if (user == null) throw new UserNotFound(username + " not found");

        validateUserName(username);
        validateAmountField(amount);
        validateWithDraw(amount);

        Wallet wallet = user.getWallet();
        BigDecimal balance = wallet.getBalance();
        validateSufficientFund(balance,amount);

        wallet.setBalance(balance.subtract(amount));
        userRepository.save(user);

    }

    @Override
    public BigDecimal checkWalletBalance(String username) {
        validateUserName(username);
        User user = userRepository.findUserByUserName(username);
        if (user == null) throw new UserNotFound(username + " not found");

        Wallet wallet = user.getWallet();

        return wallet.getBalance();

    }


}
