insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/apts,/apartments,/propertyInfos,/pis,/roomInfos,/ris', 'Y', 'Y', 'Y', 'N'),
('user', '/apts,/apartments,/propertyInfos,/pis,/roomInfos,/ris', 'Y', 'N', 'N', 'N')
;
commit;

insert into users (name, password, first_name, last_name, email) values
('A', '25f9e794323b453885f5181f1b624d0b', 'A', 'A', 'A@apartment.com'),
('B', '25f9e794323b453885f5181f1b624d0b', 'B', 'B', 'B@apartment.com'),
('C', '25f9e794323b453885f5181f1b624d0b', 'C', 'C', 'C@apartment.com')
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