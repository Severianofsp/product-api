package br.com.severiano.productapi.factory;

import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.severiano.productapi.builder.ProductDtoBuilder.buildDefaultProductDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductFactoryTest {

    @InjectMocks
    private ProductFactory productFactory;

    @Test
    @DisplayName("Should build Product")
    void shouldBuildProduct() {
        //Data
        ProductDto productDto = buildDefaultProductDto().build();

        //Action
        Product result = productFactory.buildProduct(productDto);

        //Result
        assertThat(result.getName()).isEqualTo(productDto.getName());
        assertThat(result.getPrice()).isEqualTo(productDto.getPrice());
        assertThat(result.getWeight()).isEqualTo(productDto.getWeight());
    }
}
