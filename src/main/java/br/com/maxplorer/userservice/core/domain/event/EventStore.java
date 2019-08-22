package br.com.maxplorer.userservice.core.domain.event;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "event_store")
public class EventStore {

    @Id
    @Column(name = "event_id")
    private UUID eventId;

    @Column(name = "aggregate_id")
    private UUID aggregateId;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_version")
    private Integer eventVersion;

    @Column(name = "occurred_on")
    private OffsetDateTime occurredOn;

    @Type(type = "jsonb")
    @Column(name = "payload")
    private String payload;
}
