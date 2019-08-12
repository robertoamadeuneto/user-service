package br.com.maxplorer.userservice.port.adapter.messaging;

import br.com.maxplorer.userservice.domain.event.Event;
import br.com.maxplorer.userservice.domain.event.EventPublisher;
import br.com.maxplorer.userservice.domain.event.EventStore;
import br.com.maxplorer.userservice.domain.event.EventStoreRepository;
import br.com.maxplorer.userservice.domain.exception.InternalServerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MessagingStream implements EventPublisher {

    private MessagingChannels messagingChannels;
    private EventStoreRepository eventStoreRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public MessagingStream(MessagingChannels messagingChannels,
                           EventStoreRepository eventStoreRepository,
                           ObjectMapper objectMapper) {
        this.messagingChannels = messagingChannels;
        this.eventStoreRepository = eventStoreRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public void publish(Event event) {

        eventStoreRepository.save(toEventStore(event));

        messagingChannels.userServiceOutput().send(MessageBuilder
                .withPayload(event)
                .setHeader("eventId", event.eventId())
                .setHeader("aggregateId", event.aggregateId())
                .setHeader("eventType", event.eventType())
                .setHeader("eventVersion", event.eventVersion())
                .setHeader("occurredOn", event.occurredOn())
                .build());
    }

    private EventStore toEventStore(Event event) {

        final String eventPayload;

        try {
            eventPayload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new InternalServerException(e.getMessage());
        }

        return new EventStore(event.eventId(),
                event.aggregateId(),
                event.eventType(),
                event.eventVersion(),
                event.occurredOn(),
                eventPayload);
    }
}
