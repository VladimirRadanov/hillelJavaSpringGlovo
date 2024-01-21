SELECT * FROM orders;
SELECT * FROM product;
SELECT * FROM orders_product;

SELECT orders.id, orders.date, orders.cost,
       product.id, product.name, product.cost
FROM orders
         INNER JOIN orders_product ON orders.id = orders_product.order_id
         INNER JOIN product ON product.id = orders_product.product_id;

SELECT distinct product_id from orders_product where order_id = 1;

SELECT product.id, product.name, product.cost
FROM product
        INNER JOIN orders_product ON product.id = orders_product.product_id
        AND orders_product.order_id = 2;