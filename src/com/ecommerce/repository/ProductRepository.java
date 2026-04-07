package com.ecommerce.repository;

import com.ecommerce.model.Product;
import com.ecommerce.util.CsvParser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    /*
    *CURD OPERATIONS ON PRODUCT
    */

    private final List<Product> products;

    ProductRepository() throws IOException {
        CsvParser csvParser = new CsvParser();
        products = CsvParser.getProductsFromCsv();
    }

    public Product save(Product product){
        this.products.add(product);
        return product;
    }

    public Optional<Product> getById(int id){
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
    public List<Product> getAll(){
        return this.products;
    }
}
