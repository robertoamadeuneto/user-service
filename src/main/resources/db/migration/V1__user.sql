create table "user" (
  id            uuid not null primary key,
  first_name    varchar(50) not null,
  middle_name   varchar(50) not null,
  last_name     varchar,
  date_of_birth timestamp,
  genre         varchar(6) not null,
  email         varchar(50) not null,
  password      varchar(50) not null
);