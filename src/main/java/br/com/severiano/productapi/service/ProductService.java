package br.com.severiano.productapi.service;

import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.model.Product;

import java.util.List;

public interface ProductService {

    void delete(Long id);

    List<Product> findAll();

    Product findById(Long id);

    void save(ProductDto productDto);

    Product update(Long id, ProductDto productDto);
}
