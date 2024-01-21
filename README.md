# junior-crud-service



#### for local run
- active profile "default"
- testing profile "dev"
- postgresDB run on local host

#### API

- Create Order example curl
```
curl --location 'http://localhost:8080/api/v1/orders' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E53FC7C5AE5AE37CF9F07A60A245398E' \
--data '{
    "cost": 13.0,
    "date": "2024-01-10",
    "products": [
        {
            "name": "product3",
            "cost": 7.0
        },
        {
            "name": "product4",
            "cost": 7.0
        }
    ]
}'
```

- Get Order example curl
```
curl --location 'http://localhost:8080/api/v1/orders/5' \
--header 'Cookie: JSESSIONID=6rsUIySU_K1r5ayTX8bkqQxca8JWOjMwqOD0ytXL'
```
