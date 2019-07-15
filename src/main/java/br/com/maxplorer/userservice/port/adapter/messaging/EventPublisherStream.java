package br.com.maxplorer.userservice.port.adapter.messaging;

import br.com.maxplorer.userservice.domain.event.Event;
import br.com.maxplorer.userservice.domain.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherStream implements EventPublisher {

    private MessagingChannels messagingChannels;

    @Autowired
    public EventPublisherStream(MessagingChannels messagingChannels) {
        this.messagingChannels = messagingChannels;
    }

    @Override
    public void publish(Event event) {
        messagingChannels.userServiceOutput().send(MessageBuilder
                .withPayload(event)
                .setHeader("eventId", event.eventId().id())
                .setHeader("aggregateId", event.aggregateId())
                .setHeader("eventType", event.eventType())
                .setHeader("eventVersion", event.eventVersion())
                .setHeader("occurredOn", event.occurredOn())
                .build());
    }
}
