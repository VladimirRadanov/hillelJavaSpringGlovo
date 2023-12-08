SELECT * FROM orders;
SELECT * FROM products;
SELECT * FROM order_products;

SELECT orders.id, orders.date, orders.cost,
       products.id, products.name, products.cost
FROM orders
         INNER JOIN order_products ON orders.id = order_products.order_id
         INNER JOIN products ON products.id = order_products.product_id;

SELECT distinct product_id from order_products where order_id = 1;

SELECT products.id, products.name, products.cost
FROM products
        INNER JOIN order_products ON products.id = order_products.product_id
        AND order_products.order_id = 2;