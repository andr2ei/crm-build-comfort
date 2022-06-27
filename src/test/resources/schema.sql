create table statuses (
    status_id identity not null primary key,
    status_name varchar(25) not null unique
);

create table leads (
    lead_id int not null primary key auto_increment,
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
    foreign key (status_id) references statuses (status_id)
);

create table products (
    product_id int not null primary key auto_increment,
    product_name varchar(50) not null,
    price decimal(10,2) not null,
    count int not null,
    product_comment varchar(1000),
    lead_id int not null,
    is_service boolean not null,
    foreign key (lead_id) references leads (lead_id)
);