CREATE TABLE section4.person
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(36) NOT NULL,
  first_name VARCHAR(36) NOT NULL
);
CREATE INDEX person_last_name_index ON section4.person (last_name);