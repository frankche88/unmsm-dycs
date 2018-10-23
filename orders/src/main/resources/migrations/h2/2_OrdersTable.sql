CREATE TABLE orders (
  order_id bigint NOT NULL AUTO_INCREMENT,
  buyer_id bigint NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  address VARCHAR(50) NOT NULL,
  credit_card VARCHAR(50) NOT NULL,
  credit_card_authcode VARCHAR(25)  NOT NULL,
  order_date DATETIME NOT NULL,
  PRIMARY KEY (order_id, buyer_id)
);

INSERT INTO orders(buyer_id, first_name, last_name, address, credit_card, credit_card_authcode, order_date)
VALUES(1, 'Pedro', 'Gomez', 'La casa de mama', '5464-5464-5464-5464', '464', '2018-10-01 00:00:00');
