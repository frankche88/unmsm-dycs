CREATE TABLE orders (
  order_id bigint NOT NULL AUTO_INCREMENT,
  address VARCHAR(50) NOT NULL,
  order_date DATETIME NOT NULL,
  order_status int NOT NULL,
  payment_authcode VARCHAR(25)  NOT NULL,
  order_total DECIMAL(5,2)  NOT NULL,
  currency VARCHAR(4) NOT NULL,
  buyer_id BIGINT NOT NULL,
  PRIMARY KEY (order_id),
    CONSTRAINT FK_order_buyer_id FOREIGN KEY(buyer_id) REFERENCES buyers(buyer_id)
);

INSERT INTO orders(address, order_date, order_status, payment_authcode, order_total, currency,buyer_id) VALUES('La casa de mama', '2018-10-01 00:00:00', 1, 'asasdada-asdas-5464',20.6, 'PEN',1);
