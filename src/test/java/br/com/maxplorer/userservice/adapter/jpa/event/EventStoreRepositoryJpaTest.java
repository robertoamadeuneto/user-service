package br.com.maxplorer.userservice.adapter.jpa.event;

import br.com.maxplorer.userservice.core.domain.event.EventStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventStoreRepositoryJpaTest {

    @Mock
    private EventStoreRepositoryJpaSpringData eventStoreRepositoryJpaSpringData;

    private EventStoreRepositoryJpa eventStoreRepositoryJpa;

    @Before
    public void setUp() {
        eventStoreRepositoryJpa = new EventStoreRepositoryJpa(eventStoreRepositoryJpaSpringData);
    }

    @Test
    public void shouldSave() {

        final EventStore eventStore = EventStoreRepositoryJpaTestFixture.eventStore();

        eventStoreRepositoryJpa.save(eventStore);

        verify(eventStoreRepositoryJpaSpringData).save(same(eventStore));
    }
}
