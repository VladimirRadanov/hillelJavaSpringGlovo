CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    date DATE,
    cost NUMERIC(8,2)
);

CREATE TABLE IF NOT EXISTS products(
    id SERIAL PRIMARY KEY,
    name TEXT,
    cost NUMERIC(8,2)
);

-- orders - products relationship
CREATE TABLE IF NOT EXISTS order_products(
    order_id INTEGER REFERENCES orders(id),
    product_id INTEGER REFERENCES products(id),
    CONSTRAINT order_product_pk PRIMARY KEY(order_id, product_id)
);

-- DB cleanup
-- DROP TABLE if EXISTS orders, products, order_products;