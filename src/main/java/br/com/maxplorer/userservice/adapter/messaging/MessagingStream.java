package br.com.maxplorer.userservice.adapter.messaging;

import br.com.maxplorer.userservice.core.domain.event.Event;
import br.com.maxplorer.userservice.core.domain.event.EventPublisher;
import br.com.maxplorer.userservice.core.domain.event.EventStore;
import br.com.maxplorer.userservice.core.domain.event.EventStoreRepository;
import br.com.maxplorer.userservice.core.domain.exception.InternalServerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class MessagingStream implements EventPublisher {

    private final MessagingChannels messagingChannels;
    private final EventStoreRepository eventStoreRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void publish(final Event event) {
        eventStoreRepository.save(toEventStore(event));

        messagingChannels.userServiceOutput().send(MessageBuilder
                .withPayload(event)
                .setHeader("eventId", event.eventId())
                .setHeader("aggregateId", event.aggregateId())
                .setHeader("eventType", event.eventType())
                .setHeader("eventVersion", event.eventVersion())
                .setHeader("occurredOn", event.occurredOn())
                .setHeader("applicationName", event.applicationName())
                .build());
    }

    private EventStore toEventStore(final Event event) {
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
                event.applicationName(),
                eventPayload);
    }
}
