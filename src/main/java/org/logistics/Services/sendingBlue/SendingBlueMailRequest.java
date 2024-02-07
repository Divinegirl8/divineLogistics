package org.logistics.Services.sendingBlue;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SendingBlueMailRequest {
    private List<SendingBlueReceiver> listOfMailReceiver = new ArrayList<>();
    private SendingBlueSender sender;
    private String subject;

    private String topic;
}
