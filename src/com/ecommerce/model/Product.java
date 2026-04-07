package com.ecommerce.model;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private double maxRetailPrice;
    private double discountPercentage;
    private boolean isAvailable;
    private String company;
    private String category;
    private int manufacturedYear;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public double getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public Product setMaxRetailPrice(double maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
        return this;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public Product setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Product setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Product setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getManufacturedYear() {
        return manufacturedYear;
    }

    public Product setManufacturedYear(int manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(maxRetailPrice, product.maxRetailPrice) == 0 && Double.compare(discountPercentage, product.discountPercentage) == 0 && isAvailable == product.isAvailable && manufacturedYear == product.manufacturedYear && Objects.equals(name, product.name) && Objects.equals(company, product.company) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxRetailPrice, discountPercentage, isAvailable, company, category, manufacturedYear);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxRetailPrice=" + maxRetailPrice +
                ", discountPercentage=" + discountPercentage +
                ", isAvailable=" + isAvailable +
                ", company='" + company + '\'' +
                ", category='" + category + '\'' +
                ", manufacturedYear=" + manufacturedYear +
                '}';
    }
}
