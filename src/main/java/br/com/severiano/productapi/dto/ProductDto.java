package br.com.severiano.productapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDto {

    @NotBlank(message = "Name must be informed")
    private String name;

    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;

    @Min(value = 0, message = "Weight must be greater than 0")
    private Double weight;
}
