package br.com.constock.userservice.port.adapter.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessagingChannels {

    String USER_SERVICE_OUTPUT = "user-service-output";

    @Output(USER_SERVICE_OUTPUT)
    MessageChannel userServiceOutput();
}
