package org.logistics.Services.sendingBlue;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@RequiredArgsConstructor
public class SendingBlueImpl implements SendingBlueService {

    private  final MailConfig mailConfig;


    @Override
    public void sendingBlueMailSender(SendingBlueMailRequest sendingRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", mailConfig.getApiKey());
        HttpEntity<SendingBlueMailRequest> requestEntity = new HttpEntity<>(sendingRequest, headers);

        var response = restTemplate.postForEntity(mailConfig.getUrl(), requestEntity, String.class);

    }
}
