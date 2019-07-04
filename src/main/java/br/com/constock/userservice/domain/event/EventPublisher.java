package br.com.constock.userservice.domain.event;

public interface EventPublisher {

    void publish(Event event);
}
