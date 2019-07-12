package br.com.constock.userservice.domain.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static EventPublisher eventPublisher;

    public static EventPublisher eventPublisher() {
        return eventPublisher;
    }

    @Override
    public synchronized void setApplicationContext(ApplicationContext applicationContext) {
        defineApplicationContext(applicationContext);
    }

    public static void defineEventPublisher(EventPublisher eventPublisher) {
        EventRegistry.eventPublisher = eventPublisher;
    }

    private static void defineApplicationContext(ApplicationContext applicationContext) {
        EventRegistry.applicationContext = applicationContext;
        defineEventPublisher(applicationContext()
                .map(a -> a.getBean(EventPublisher.class))
                .orElseThrow(() -> new RuntimeException("Application Context não foi definido para o Registro de Evento")));
    }

    private static Optional<ApplicationContext> applicationContext() {
        return Optional.ofNullable(applicationContext);
    }
}