drop table if exists stock;

create table  stock (
    id bigint not null unique,
    name varchar(25) not null,
    price double not null,
    quantity bigint,
    date_created timestamp,
    date_updated timestamp,
    primary key (id)
);


insert  into stock (id, name, price, quantity, date_created)
values(20, 'LSA', 2000, 10,current_timestamp()),
(21, 'WSA', 5000, 10,current_timestamp()),
(22, 'QSA', 12000, 10,current_timestamp());