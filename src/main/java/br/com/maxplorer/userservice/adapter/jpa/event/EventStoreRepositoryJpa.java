package br.com.maxplorer.userservice.adapter.jpa.event;

import br.com.maxplorer.userservice.core.domain.event.EventStore;
import br.com.maxplorer.userservice.core.domain.event.EventStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventStoreRepositoryJpa implements EventStoreRepository {

    private final EventStoreRepositoryJpaSpringData eventStoreRepositoryJpaSpringData;

    @Autowired
    public EventStoreRepositoryJpa(EventStoreRepositoryJpaSpringData eventStoreRepositoryJpaSpringData) {
        this.eventStoreRepositoryJpaSpringData = eventStoreRepositoryJpaSpringData;
    }

    @Override
    public void save(EventStore eventStore) {
        eventStoreRepositoryJpaSpringData.save(eventStore);
    }
}
