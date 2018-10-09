CREATE TABLE buyers (
  buyer_id bigint NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(20),
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (buyer_id)
);

CREATE INDEX IX_buyer_last_first_name ON buyers (last_name, first_name);

INSERT INTO buyers(first_name, last_name, user_name) VALUES('Juan', 'Pérez', '123456789');
