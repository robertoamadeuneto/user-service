create table event_type (event_type_id varchar(50) not null primary key);

create table event_store (
  event_id      uuid not null primary key,
  aggregate_id  uuid not null,
  event_type    varchar(50) not null references event_type(event_type_id),
  event_version int not null,
  occurred_on   timestamp with time zone not null,
  payload       jsonb not null
);