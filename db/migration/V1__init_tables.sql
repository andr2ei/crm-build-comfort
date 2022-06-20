create table statuses (
    status_id serial not null primary key,
    status_name varchar(25) not null
);

create table leads (
    lead_id serial not null primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    phone varchar(20) not null,
    address varchar(200),
    email varchar(50),
    storage_unit varchar(200),
    trade_price int,
    discount smallint not null default 0,
    status_id int not null default 1,
    creation_date timestamp,
    foreign key (status_id) references statuses (status_id)
);

create table products (
    product_id serial not null primary key,
    product_name varchar(50) not null,
    price int not null,
    count int not null,
    product_comment varchar(1000),
    lead_id int not null,
    foreign key (lead_id) references leads (lead_id)
);

create table services (
    service_id serial not null primary key,
    service_name varchar(50) not null,
    price int not null,
    service_comment varchar(1000),
    lead_id int not null,
    foreign key (lead_id) references leads (lead_id)
);