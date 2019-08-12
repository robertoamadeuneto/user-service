package br.com.maxplorer.userservice.domain.event;

public interface EventStoreRepository {

    void save(EventStore eventStore);
}
