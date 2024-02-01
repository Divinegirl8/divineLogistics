package org.logistics.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class Wallet {
   private BigDecimal balance = BigDecimal.ZERO;
}
