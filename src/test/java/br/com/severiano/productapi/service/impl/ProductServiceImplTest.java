package br.com.severiano.productapi.service.impl;

import br.com.severiano.productapi.builder.ProductBuilder;
import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.exception.ProductNotFoundException;
import br.com.severiano.productapi.factory.ProductFactory;
import br.com.severiano.productapi.model.Product;
import br.com.severiano.productapi.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.severiano.productapi.builder.ProductDtoBuilder.buildDefaultProductDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductFactory productFactory;

    @Test
    @DisplayName("Should save product")
    void shouldSaveProduct() {
        //Data
        ProductDto productDto = buildDefaultProductDto().build();
        Product product = ProductBuilder.buildDefaultProduct().build();

        when(productFactory.buildProduct(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        //Result
        assertDoesNotThrow(() -> productService.save(productDto));
    }

    @Test
    @DisplayName("Should find all products")
    void shouldFindAllProducts() {
        //Data
        List<Product> productList = ProductBuilder.buildDefaultProduct().list();

        when(productRepository.findAll()).thenReturn(productList);

        //Action
        List<Product> result = productService.findAll();

        //Result
        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(productList);
    }

    @Test
    @DisplayName("Should find empty product list")
    void shouldFindEmptyProductList() {
        //Data
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        //Action
        List<Product> result = productService.findAll();

        //Result
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should find product by id")
    void shouldFindProductById() {
        //Data
        Optional<Product> product = ProductBuilder.buildDefaultProduct().optional();

        when(productRepository.findById(1L)).thenReturn(product);

        //Action
        Product result = productService.findById(1L);

        //Result
        assertThat(result).isEqualTo(product.orElse(null));
    }

    @Test
    @DisplayName("Should not found product by id")
    void shouldNotFoundProductById() {
        //Data
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        //Result
        assertThrows(ProductNotFoundException.class, () -> productService.findById(1L));
    }

    @Test
    @DisplayName("Should delete product by id")
    void shouldDeleteProductById() {
        //Result
        assertDoesNotThrow(() -> productService.delete(1L));
    }

    @Test
    @DisplayName("Should not found product by id When delete product")
    void shouldNotFoundProductByIdWhenDeleteProduct() {
        //Data
        doThrow(ProductNotFoundException.class).when(productRepository).deleteById(1L);

        //Result
        assertThrows(ProductNotFoundException.class, () -> productService.delete(1L));
    }

    @Test
    @DisplayName("Should update product by id")
    void shouldUpdateProductById() {
        //Data
        Optional<Product> productOptional = ProductBuilder.buildDefaultProduct().optional();
        ProductDto productDto = buildDefaultProductDto().withName("Short").build();

        when(productRepository.findById(1L)).thenReturn(productOptional);

        //Action
        Product result = productService.update(1L, productDto);

        //Result
        assertThat(result.getName()).isEqualTo(productDto.getName());
    }

    @Test
    @DisplayName("Should update product by id When update product")
    void shouldNotFoundProductIdWhenUpdateProduct() {
        //Data
        ProductDto productDto = buildDefaultProductDto().build();
        doThrow(ProductNotFoundException.class).when(productRepository).findById(1L);

        //Result
        assertThrows(ProductNotFoundException.class, () -> productService.update(1L, productDto));
    }
}
