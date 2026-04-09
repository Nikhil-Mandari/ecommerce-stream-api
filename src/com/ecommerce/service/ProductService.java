package com.ecommerce.service;

import com.ecommerce.exception.ProductExistsException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    /*
     CRUD methods
    */
    // Save
    Product save(Product product) throws ProductExistsException;

    //Get by id
    Product getById(int id) throws ProductNotFoundException;

    //Get all
    List<Product> getAll();

    //Product Update
    Product update(int id, Product product) throws ProductNotFoundException;
    //Delet

    void delete(int id) throws ProductNotFoundException;

    //Get all available products based on availability
    List<Product> getProductByAvailability(boolean isAvailable);

    //Get all products belonging to a given category
    List<Product> getProductsByCategory(String category);


    //Get all products with price greater than a given value
    List<Product> getProductsByPriceGreaterThan(int price);

    //Get names of all products
    List<String> getAllProductsName();

    //Count how many products are available
    Long getTotalProductsCount();

    //Check if there is any product from a given company
    boolean existsProductsByCompany(String company);

    //Check if all products are available
    boolean areAllProductsAvailable();

    //Get the first product safely
    Optional<Product> getFirstProduct();

    //Get all unique categories
    List<String> getAllUniqueCategories();

    //Get top N most expensive products
    List<Product> getTopMostExpensiveProducts();

    //Sort products by price in ascending order
    List<Product> sortProductsByPriceAsc();

    //Sort products by name in descending order
    List<Product> sortProductsByNameDsc();

    //Get total inventory value (sum of all product prices)
    double getTotalInventoryValue();

    //Get total price after applying discounts
    double getTotalPriceAfterDiscount();

    //Get all products manufactured after a given year
    List<Product> getProductsManufacturedAfter(int year);

    /*
      Get all products that are:
      - available
      - and price greater than a given value
       */
    List<Product> getAvailableProductsWithPriceGreaterThan(double price);

    //Count number of products in each category
    Map<String, Long> getProductsCountFromCategory();

    //Group all products by category
    Map<String, List<Product>> getProductsGroupedByCategory();

    //Group all products by company
    Map<String, List<Product>> getProductsGroupedByCompany();

    /* Partition products into:
    - available
    - unavailable
     */
    Map<Boolean, List<Product>> getProductsPartitionedByAvailability();

    //Find the most expensive product
    Optional<Product> getMostExpensiveProduct();

    //Find the cheapest product
    Optional<Product> getCheapestProduct();

    //Create a Map of product ID to Product
    Optional<Product> getProductById(int id);

    //Get top 3 most expensive products in each category
    Map<String, List<Product>> getTopThreeExpensiveProductsByCatrgory();

}
