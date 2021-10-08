package com.example.customlistviewexample;

public class Product {

    private String name;
    private String description;
    private String type;
    private double price;
    private boolean sale;

    public Product(String name, String description, String type, double price, boolean sale) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }
}
