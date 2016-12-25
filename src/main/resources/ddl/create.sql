DROP TABLE if EXISTS user;
create table if not exists user (
   id          int          not null auto_increment,
   username    varchar(255) not null,
   password    varchar(255) not null,
   primary key (id)
);