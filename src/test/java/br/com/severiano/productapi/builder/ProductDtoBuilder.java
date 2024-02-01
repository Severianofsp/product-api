package br.com.severiano.productapi.builder;

import br.com.severiano.productapi.dto.ProductDto;

public class ProductDtoBuilder {

    private ProductDto productDto;

    public static ProductDtoBuilder buildDefaultProductDto() {
        ProductDtoBuilder builder = new ProductDtoBuilder();
        builder.productDto = ProductDto.builder()
                .name("Camisa")
                .price(10.00)
                .weight(0.500)
                .build();
        return builder;
    }

    public ProductDtoBuilder withName(String name){
        this.productDto.setName(name);
        return this;
    }

    public ProductDto build() {
        return this.productDto;
    }
}
