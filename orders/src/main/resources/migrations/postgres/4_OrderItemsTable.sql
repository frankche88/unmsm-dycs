CREATE TABLE order_items (
  id SERIAL PRIMARY KEY,
  order_id bigint NOT NULL,
  product_id bigint NOT NULL,
  product_name VARCHAR(50) NOT NULL,
  picture_url VARCHAR(50) NOT NULL,
  unit_price DECIMAL(10,2) NOT NULL,
  currency VARCHAR(4) NOT NULL,
  units INTEGER NOT NULL,
    CONSTRAINT FK_items_order_id FOREIGN KEY(order_id) REFERENCES orders(order_id)
);

insert into order_items (
  id,
  order_id,
  product_id,
  product_name,
  picture_url,
  unit_price,
  currency,
  units) values (1,1,122,'un producto', 'una ruta de imagen',20.67,'PEN',200);

insert into order_items (
  id,
  order_id,
  product_id,
  product_name,
  picture_url,
  unit_price,
  currency,
  units) values (2,1,122,'otro producto', 'una ruta de imagen',20.67,'PEN',200);
