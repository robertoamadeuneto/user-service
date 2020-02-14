create table genre (genre_id varchar(50) not null primary key);

insert into genre (genre_id) values
('FEMALE'),
('MALE');

create table status (status_id varchar(50) not null primary key);

insert into status (status_id) values
('PENDING'),
('ACTIVE'),
('INACTIVE');

create table "user" (
  id             uuid not null primary key,
  first_name     varchar(50) not null,
  last_name      varchar not null,
  date_of_birth  timestamp not null,
  genre_id       varchar(6) not null references genre(genre_id),
  email          varchar(50) not null,
  status_id      varchar(50) not null references status(status_id)
);

create table password (
  user_id  uuid not null references "user"(id),
  password varchar(60) not null,
  active   boolean not null,

  primary key (user_id, password)
);

insert into event_type (event_type_id) values
('user-service.user.created'),
('user-service.user.activated');