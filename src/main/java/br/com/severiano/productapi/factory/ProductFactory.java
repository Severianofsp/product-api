package br.com.severiano.productapi.factory;

import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductFactory {

    public Product buildProduct(ProductDto productDto){
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
