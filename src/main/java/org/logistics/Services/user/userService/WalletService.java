package org.logistics.Services.user.userService;

import java.math.BigDecimal;

public interface WalletService {
    void addMoney(String username,BigDecimal amount);
    void deductMoney(String username,BigDecimal amount);
    BigDecimal checkWalletBalance(String username);
}
