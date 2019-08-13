create table "user" (
  id            uuid not null primary key,
  first_name    varchar(50) not null,
  last_name     varchar,
  date_of_birth timestamp,
  genre         varchar(6) not null,
  email         varchar(50) not null
);

create table password (
  user_id  uuid not null references "user"(id),
  password varchar(60) not null,
  active   boolean not null,

  primary key (user_id, password)
);

insert into event_type (event_type_id) values
('user.created');