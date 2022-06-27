insert into statuses (status_name) values ('status name 1');
insert into statuses (status_name) values ('status name 2');
insert into statuses (status_name) values ('status name 3');

insert into leads (first_name, last_name, phone, address, email,
                    storage_unit, trade_price, discount, status_id, creation_date) values
('first name 1', 'last name 1', 'phone 1', 'address 1', 'email 1', 'storage unit 1', 10000.50, 3, 1, '2022-06-26'),
('first name 2', 'last name 2', 'phone 2', 'address 2', 'email 2', 'storage unit 2', 20000.50, 4, 1, '2022-06-25'),
('first name 3', 'last name 3', 'phone 3', 'address 3', 'email 3', 'storage unit 3', 10000.50, 5, 3, '2022-06-24');

insert into products (product_name, price, count, product_comment, lead_id, is_service) values
('product name 1', 100000.55, 2, 'comment 1', 1, true),
('product name 2', 200000.55, 4, 'comment 2', 2, true),
('product name 3', 300000.55, 3, 'comment 3', 1, false),
('product name 4', 400000.55, 1, 'comment 4', 3, false);