package br.com.maxplorer.userservice.port.adapter.messaging;

import br.com.maxplorer.userservice.domain.event.EventPublisher;
import br.com.maxplorer.userservice.domain.user.UserCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessagingStreamTest {

    @Autowired
    private EventPublisher publisher;

    @Autowired
    private MessagingChannels channels;

    @Autowired
    private MessageCollector collector;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldPublishUserCreatedEvent() throws InterruptedException, IOException {

        final UserCreatedEvent event = MessagingStreamTestFixture.userCreatedEvent();

        publisher.publish(event);

        final Message<?> message = collector.forChannel(channels.userServiceOutput()).poll(1L, TimeUnit.MILLISECONDS);

        final UserCreatedEvent capturedEvent = mapper.readValue((String) Objects.requireNonNull(message).getPayload(), UserCreatedEvent.class);

        assertThat(message.getHeaders()).containsKey("eventId");
        assertThat(message.getHeaders().get("occurredOn")).isNotNull();
        assertThat(message.getHeaders().get("eventVersion")).isEqualTo(event.eventVersion());
        assertThat(message.getHeaders().get("aggregateId")).isEqualTo(event.aggregateId());
        assertThat(capturedEvent).isEqualToComparingFieldByFieldRecursively(event);
    }
}
