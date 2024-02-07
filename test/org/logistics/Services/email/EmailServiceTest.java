package org.logistics.Services.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test void testOne(){
        emailService.sendEmail("iamoluchimercy@gmail.com","subject","i am body");
    }

}