INSERT INTO orders (date, cost)
VALUES ('2023-12-07', 100.80),
       ('2023-12-08', 200.80),
       ('2023-12-09', 300.80);


INSERT INTO orders (id, date, cost)
VALUES (22, '2023-12-07', 100.80);

INSERT INTO PRODUCT(name, cost)
VALUES ('name1', 10.10),
       ('name2', 20.20),
       ('name3', '50.45');

INSERT INTO orders_product(order_id, product_id)
VALUES (1,1),
       (1,2),
       (1,3),
       (2,2),
       (2,3),
       (3,3),
       (3,1);