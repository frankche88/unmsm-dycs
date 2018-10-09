CREATE TABLE order_items (
  id bigint NOT NULL AUTO_INCREMENT,
  order_id bigint NOT NULL,
  product_id bigint NOT NULL,
  product_name VARCHAR(50) NOT NULL,
  picture_url VARCHAR(50) NOT NULL,
  unit_price DECIMAL(10,2) NOT NULL,
  currency VARCHAR(4) NOT NULL,
  units INTEGER NOT NULL,
    PRIMARY KEY (id, order_id),
    CONSTRAINT FK_items_order_id FOREIGN KEY(order_id) REFERENCES orders(order_id)
);
