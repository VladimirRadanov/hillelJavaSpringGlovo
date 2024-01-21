/*drop table if exists order_products;
drop table if exists orders;
drop table if exists product;

CREATE TABLE IF NOT EXISTS orders
(
    id   SERIAL PRIMARY KEY,
    date DATE,
    cost NUMERIC
);

CREATE TABLE IF NOT EXISTS product
(
    id   SERIAL PRIMARY KEY,
    orders integer references orders(id),
    orders_key integer,
    name TEXT,
    cost NUMERIC
);
*/
