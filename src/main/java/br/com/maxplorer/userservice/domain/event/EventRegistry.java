package br.com.maxplorer.userservice.domain.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static EventPublisher eventPublisher;

    @Override
    public synchronized void setApplicationContext(ApplicationContext applicationContext) {
        defineApplicationContext(applicationContext);
    }

    private static void defineApplicationContext(ApplicationContext applicationContext) {
        EventRegistry.applicationContext = applicationContext;
        defineEventPublisher(Optional.ofNullable(applicationContext())
                .map(ac -> ac.getBean(EventPublisher.class))
                .orElseThrow(() -> new RuntimeException("Application Context wasn't defined for the Event Registry")));
    }

    public static void defineEventPublisher(EventPublisher eventPublisher) {
        EventRegistry.eventPublisher = eventPublisher;
    }

    private static ApplicationContext applicationContext() {
        return applicationContext;
    }

    public static EventPublisher eventPublisher() {
        return eventPublisher;
    }
}