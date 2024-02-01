package br.com.severiano.productapi.service.impl;

import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.exception.ProductNotFoundException;
import br.com.severiano.productapi.factory.ProductFactory;
import br.com.severiano.productapi.model.Product;
import br.com.severiano.productapi.repository.ProductRepository;
import br.com.severiano.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductFactory productFactory;
    private final ProductRepository productRepository;

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = productFactory.buildProduct(productDto);
        productRepository.save(product);
    }

    @Override
    public Product update(Long id, ProductDto product) {
        Product productDb = findById(id);

        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setWeight(product.getWeight());
        productDb.setModifiedDate(LocalDateTime.now());

        productRepository.save(productDb);
        return productDb;
    }
}
