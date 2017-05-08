CREATE TABLE section4_2.address
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  address1 VARCHAR(36) NOT NULL,
  address2 VARCHAR(36),
  city VARCHAR(36) NOT NULL,
  state VARCHAR(36),
  zip INT
);

ALTER TABLE section4_2.person ADD address INT NULL;
ALTER TABLE section4_2.person
  ADD CONSTRAINT person_address_id_fk
FOREIGN KEY (address) REFERENCES address (id);