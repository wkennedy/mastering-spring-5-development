CREATE TABLE section4_2.person
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(36) NOT NULL,
  first_name VARCHAR(36) NOT NULL
);
CREATE INDEX person_last_name_index ON section4_2.person (last_name);