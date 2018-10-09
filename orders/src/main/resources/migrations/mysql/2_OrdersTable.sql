CREATE TABLE orders (
  order_id bigint NOT NULL AUTO_INCREMENT,
  address VARCHAR(50) NOT NULL,
  order_date DATETIME NOT NULL,
  order_status int NOT NULL,
  payment_authcode VARCHAR(25)  NOT NULL,
  order_total DECIMAL(5,2)  NOT NULL,
  buyer_id BIGINT NOT NULL,
  PRIMARY KEY (order_id),
    INDEX IX_bank_account_customer_id(customer_id),
    UNIQUE INDEX UQ_bank_account_number(number),
    CONSTRAINT FK_order_buyer_id FOREIGN KEY(buyer_id) REFERENCES buyers(buyer_id)
);

INSERT INTO orders(address, order_date, order_status, payment_authcode, order_total, buyer_id) VALUES('La casa de mama', '2018-10-01 00:00:00', 1, 'asasdada-asdas-5464',20.6, 1);
