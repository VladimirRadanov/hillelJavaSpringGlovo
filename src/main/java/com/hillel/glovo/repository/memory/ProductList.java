package com.hillel.glovo.repository.memory;

import com.hillel.glovo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> products = new ArrayList<>();

    public ProductList() {
        generateProducts();
    }

    private void generateProducts() {
        products.add(new Product(1, "product1", 10));
        products.add(new Product(2, "product2", 20));
        products.add(new Product(3, "product3", 30));
    }

    public List<Product> getProducts() {
        return products;
    }
}
