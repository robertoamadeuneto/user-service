package br.com.maxplorer.userservice.adapter.jpa.event;

import br.com.maxplorer.userservice.core.domain.event.EventStore;
import br.com.maxplorer.userservice.core.domain.event.EventStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class EventStoreRepositoryJpa implements EventStoreRepository {

    private final EventStoreRepositoryJpaSpringData eventStoreRepositoryJpaSpringData;

    @Override
    public void save(final EventStore eventStore) {
        eventStoreRepositoryJpaSpringData.save(eventStore);
    }
}
