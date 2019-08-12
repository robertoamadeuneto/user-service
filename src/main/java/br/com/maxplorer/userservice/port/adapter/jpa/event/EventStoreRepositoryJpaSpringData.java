package br.com.maxplorer.userservice.port.adapter.jpa.event;

import br.com.maxplorer.userservice.domain.event.EventStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventStoreRepositoryJpaSpringData extends JpaRepository<EventStore, UUID> {

}
