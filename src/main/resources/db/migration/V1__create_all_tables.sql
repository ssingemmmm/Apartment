DROP TABLE IF EXISTS propertyInfo CASCADE;
DROP TABLE IF EXISTS roomInfo CASCADE;
DROP TABLE IF EXISTS apartment CASCADE;
CREATE TABLE apartment (
   id                SERIAL NOT NULL,
   name              VARCHAR(30) not null unique,
   lowest_price      VARCHAR(150),
   smallest_size     VARCHAR(20),
   photo			 VARCHAR(100)

);
ALTER TABLE apartment ADD CONSTRAINT apartment_pk PRIMARY KEY ( id );
CREATE TABLE propertyInfo (
   id              SERIAL NOT NULL,
   address         VARCHAR(300),
   phone_number    VARCHAR(30),
   email           VARCHAR(50),
   office_hours    VARCHAR(50),
   apartment_id  INTEGER NOT NULL UNIQUE
);
ALTER TABLE propertyInfo ADD CONSTRAINT property_pk PRIMARY KEY ( id );
CREATE TABLE roomInfo (
   id             SERIAL NOT NULL,
   apartment_id   INTEGER NOT NULL,
   size           VARCHAR(30),
   price_range	  VARCHAR(30),
   layout_photo   VARCHAR(100)
);
ALTER TABLE roomInfo ADD CONSTRAINT room_pk PRIMARY KEY ( id );
ALTER TABLE propertyInfo
   ADD CONSTRAINT property_apartment_fk FOREIGN KEY ( apartment_id )
       REFERENCES apartment ( id );
ALTER TABLE roomInfo
   ADD CONSTRAINT room_apartment_fk FOREIGN KEY ( apartment_id )
       REFERENCES apartment ( id );