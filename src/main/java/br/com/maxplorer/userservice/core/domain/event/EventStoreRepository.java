package br.com.maxplorer.userservice.core.domain.event;

public interface EventStoreRepository {

    void save(EventStore eventStore);
}
