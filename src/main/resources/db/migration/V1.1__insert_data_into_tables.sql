insert into apartment (name, lowest_price, smallest_size, photo) values
('A', '$1000', 'studio', '1'),
('B', '$2000', '1 bedroom','2'),
('C', '$3000', '1 bedroom','3'),
('D', '$4000', 'studio','4')
;
commit;


insert into propertyInfo (address, phone_number, email, office_hours, apartment_id) values
('11st st s, Arlington, VA 22202', '7031111111', 'info@A.com', '8AM - 5PM',1),
('12st st s, Arlington, VA 22202', '7032222222', 'info@B.com', '8AM - 5PM',2),
('13st st s, Arlington, VA 22202', '7033333333', 'info@C.com', '8AM - 5PM',3),
('14st st s, Arlington, VA 22202', '7034444444', 'info@D.com', '8AM - 5PM',4)
;
commit;





insert into roomInfo (apartment_id, size, price_range, layout_photo) values
(1, 'studio', '$2200-$2600', '1'),
(2, 'studio', '$2200-$2600', '2'),
(3, 'studio', '$2200-$2600', '3'),
(4, 'studio', '$2200-$2600', '4')
;
commit;