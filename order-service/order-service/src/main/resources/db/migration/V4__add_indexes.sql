


CREATE INDEX idx_orders_user_id ON orders(user_id);


CREATE UNIQUE INDEX idx_orders_order_number ON orders(order_number);


CREATE INDEX idx_order_items_order_id ON order_items(order_id);