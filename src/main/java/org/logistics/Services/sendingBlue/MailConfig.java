package org.logistics.Services.sendingBlue;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MailConfig {
    private String apiKey;
    private String url;
}
