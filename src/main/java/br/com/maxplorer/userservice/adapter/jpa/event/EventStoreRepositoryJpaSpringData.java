package br.com.maxplorer.userservice.adapter.jpa.event;

import br.com.maxplorer.userservice.core.domain.event.EventStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventStoreRepositoryJpaSpringData extends JpaRepository<EventStore, UUID> {

}
