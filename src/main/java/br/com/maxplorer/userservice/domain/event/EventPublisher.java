package br.com.maxplorer.userservice.domain.event;

public interface EventPublisher {

    void publish(Event event);
}
