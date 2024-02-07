package org.logistics.Services.sendingBlue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class MailConfigBean {

    @Bean
    public MailConfig mailConfig(){
        return new MailConfig();
    }


}
