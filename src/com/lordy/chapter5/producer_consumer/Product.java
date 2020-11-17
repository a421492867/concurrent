package com.lordy.chapter5.producer_consumer;

public class Product {

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name=" + name;
    }
}
