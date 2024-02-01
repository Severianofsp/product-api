package br.com.severiano.productapi.builder;

import br.com.severiano.productapi.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductBuilder {

    private Product product;

    public static ProductBuilder buildDefaultProduct() {
        ProductBuilder builder = new ProductBuilder();
        builder.product = Product.builder()
                .name("Camisa")
                .price(10.00)
                .weight(0.500)
                .build();
        return builder;
    }

    public Product build() {
        return this.product;
    }

    public List<Product> list() {
        return List.of(this.product);
    }

    public Optional<Product> optional(){
        return Optional.of(this.product);
    }
}
