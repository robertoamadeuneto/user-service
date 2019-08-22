package br.com.maxplorer.userservice.core.domain.event;

public interface EventPublisher {

    void publish(Event event);
}
