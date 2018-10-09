CREATE TABLE buyers (
  buyer_id bigint NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(20),
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (buyer_id),
    INDEX IX_buyer_last_first_name(last_name, first_name)
);

INSERT INTO customer(first_name, last_name, user_name) VALUES('Juan', 'Pérez', '123456789');
--INSERT INTO customer(first_name, last_name, user_name) VALUES('Carlos', 'Pérez', '123456780');
--INSERT INTO customer(first_name, last_name, user_name) VALUES('Alberto', 'Otero', '123456781');