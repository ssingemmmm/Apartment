insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/cus,/customers,/ord,/orders,/prod,/products', 'Y', 'Y', 'Y', 'N'),
('user', '/ord,/orders,/prod,/products', 'Y', 'N', 'N', 'N')
;
commit;

insert into users (name, password, first_name, last_name, email) values
('a', 'c4ca4238a0b923820dcc509a6f75849b', 'a', 'a', 'a@apartment.com'),
('b', 'c4ca4238a0b923820dcc509a6f75849b', 'b', 'b', 'B@apartment.com'),
('c', 'c4ca4238a0b923820dcc509a6f75849b', 'c', 'c', 'C@apartment.com')
;
commit;

insert into users_role values
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(1, 3)
;
commit;