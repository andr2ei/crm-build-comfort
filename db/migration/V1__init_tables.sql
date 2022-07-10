create table statuses (
    status_id serial not null primary key,
    status_name varchar(25) unique not null
);

create table leads (
    lead_id serial not null primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    phone varchar(20) not null,
    address varchar(200),
    email varchar(50),
    storage_unit varchar(200),
    trade_price decimal(10,2),
    discount smallint not null default 0,
    status_id int not null default 1,
    creation_date timestamp,
    comment varchar(1000) default '',
    storage_unit_address varchar(1000) default '',
    prepay decimal(10,2) default 0.0,
    prepay_type varchar(20) default '',
    surcharge decimal(10,2) default 0.0,
    surcharge_type varchar(20) default '',
    completed_date timestamp,
    foreign key (status_id) references statuses (status_id)
);

create table products (
    product_id serial not null primary key,
    product_name varchar(50) not null,
    price decimal(10,2) not null,
    count int not null,
    product_comment varchar(1000),
    lead_id int not null,
    is_service boolean not null,
    foreign key (lead_id) references leads (lead_id)
);