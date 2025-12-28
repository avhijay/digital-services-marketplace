CREATE TABLE order_items (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
order_id BIGINT NOT NULL,
product_id BIGINT NOT NULL,
 quantity INT NOT NULL,
unit_price DECIMAL(12,2) NOT NULL,
line_total DECIMAL(12,2) NOT NULL,

CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders(id);


);