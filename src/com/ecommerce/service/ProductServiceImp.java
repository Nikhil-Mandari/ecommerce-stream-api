package com.ecommerce.service;

import com.ecommerce.exception.ProductExistsException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    //Dependence
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws ProductExistsException {
        //Checks if product already exists by id
        productRepository.getById(product.getId())
                .ifPresent(p -> {
                    throw new ProductExistsException("Product already exist" + product.getId() + "already exist");
                });

        return productRepository.save(product);
    }
          /* Optional<Product> byId = ProductRepository.getById(product.getById);
            if (byId.isPresent())
            throw new ProductExistsException("Product not found")
            else
            ProductRepository.save(Product);
            return product;

            */

    @Override
    public Product getById(int id) throws ProductNotFoundException {
        return productRepository.getById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("product with id" + id + "not found"));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product update(int id, Product product) throws ProductNotFoundException {
        productRepository.getById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        return productRepository.update(id, product);
    }

    @Override
    public void delete(int id) throws ProductNotFoundException {
        productRepository.getById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(id);
    }

    @Override
    public List<Product> getProductByAvailability(boolean isAvailable) {
        return productRepository.getAll()
                .stream()
                .filter(p -> p.isAvailable() == isAvailable )
                .toList();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getAll()
                .stream()
                .filter(p -> p.getCategory().equals(category))
                .toList();
    }

    @Override
    public List<Product> getProductsByPriceGreaterThan(int price) {
        return productRepository.getAll()
                .stream()
                .filter(p -> p.getMaxRetailPrice() > price)
                .toList();
    }

    @Override
    public List<String> getAllProductsName() {
        return productRepository.getAll()
                .stream()
                .map(Product::getName)
                .toList();
    }

    @Override
    public Long getTotalProductsCount() {
        return (long) productRepository.getAll()
                .size();
    }

    @Override
    public boolean existsProductsByCompany(String company) {
        return this.productRepository.getAll()
                .stream()
                .anyMatch(p -> p.getCompany().equalsIgnoreCase(company));

    }

    @Override
    public boolean areAllProductsAvailable() {
        return productRepository.getAll()
                .stream()
                .allMatch(Product::isAvailable);
    }

    @Override
    public Optional<Product> getFirstProduct() {
        return productRepository.getAll()
                .stream()
                .findFirst();
    }

    @Override
    public List<String> getAllUniqueCategories() {
        return productRepository.getAll()
                .stream()
                .map(Product :: getCategory)
                .distinct()
                .toList();

    }

    @Override
    public List<Product> getTopMostExpensiveProducts() {
        return productRepository.getAll()
                .stream()
                .max(Comparator.comparingDouble(Product::getMaxRetailPrice))
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<Product> sortProductsByPriceAsc() {
        return productRepository.getAll()
                .stream()
                .sorted(Comparator.comparingDouble(Product::getMaxRetailPrice))
                .toList();
    }

    @Override
    public List<Product> sortProductsByNameDsc() {
        return productRepository.getAll()
                .stream()
                .sorted(Comparator.comparing(Product::getName).reversed())
                .toList();
    }

    @Override
    public double getTotalInventoryValue() {
        return productRepository.getAll()
                .stream()
                .mapToDouble(Product::getMaxRetailPrice)
                .sum();
    }

    @Override
    public double getTotalPriceAfterDiscount() {
        return productRepository.getAll()
                .stream()
                .mapToDouble(p -> p.getMaxRetailPrice() *(1- p.getDiscountPercentage()/100))
                .sum();
    }

    @Override
    public List<Product> getProductsManufacturedAfter(int year) {
        return productRepository.getAll()
                .stream()
                .filter(p -> p.getManufacturedYear() > year)
                .toList();
    }

    @Override
    public List<Product> getAvailableProductsWithPriceGreaterThan(double price) {
        return productRepository.getAll()
                .stream()
                .filter(Product::isAvailable)
                .filter(product -> product.getMaxRetailPrice() > price)
                .toList();
    }

    @Override
    public Map<String, Long> getProductsCountFromCategory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory,Collectors.counting()));
    }

    @Override
    public Map<String, List<Product>> getProductsGroupedByCategory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory));
    }

    @Override
    public Map<String, List<Product>> getProductsGroupedByCompany() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCompany));
    }

    @Override
    public Map<Boolean, List<Product>> getProductsPartitionedByAvailability() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.partitioningBy(Product::isAvailable));
    }

    @Override
        public Optional<Product> getMostExpensiveProduct() {
            return productRepository.getAll()
                    .stream()
                    .max(Comparator.comparingDouble(Product::getMaxRetailPrice));
        }

    @Override
    public Optional<Product> getCheapestProduct() {
        return productRepository.getAll()
                .stream()
                .min(Comparator.comparingDouble(Product::getMaxRetailPrice));
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public Map<String, List<Product>> getTopThreeExpensiveProductsByCatrgory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCompany,Collectors.collectingAndThen(
                        Collectors.toList(),List -> List.stream()
                .sorted(Comparator.comparingDouble(Product::getMaxRetailPrice).reversed())
                .limit(3)
                .toList()

                )));
    }

}

