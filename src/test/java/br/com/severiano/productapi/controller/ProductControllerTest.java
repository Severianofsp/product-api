package br.com.severiano.productapi.controller;

import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.model.Product;
import br.com.severiano.productapi.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.severiano.productapi.builder.ProductBuilder.buildDefaultProduct;
import static br.com.severiano.productapi.builder.ProductDtoBuilder.buildDefaultProductDto;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private String writeValuesAsString(Object value) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(value);
    }

    @Test
    @DisplayName("Should Get all Products")
    void shouldGetAllProducts() throws Exception {
        //Data
        List<Product> productList = buildDefaultProduct().list();

        when(productService.findAll()).thenReturn(productList);

        //Result
        mockMvc.perform(get("/v1/products")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(writeValuesAsString(productList)));
    }

    @Test
    @DisplayName("Should Get Product By Id")
    void shouldGetProductById() throws Exception {
        //Data
        Product product = buildDefaultProduct().build();

        when(productService.findById(1L)).thenReturn(product);

        //Result
        mockMvc.perform(get("/v1/products/1")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(writeValuesAsString(product)));
    }

    @Test
    @DisplayName("Should Delete Product By Id")
    void shouldDeleteProductById() throws Exception {
        //Result
        mockMvc.perform(delete("/v1/products/1")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should Update Product By Id")
    void shouldUpdateProductById() throws Exception {
        //Data
        ProductDto productDto = buildDefaultProductDto().build();
        Product product = buildDefaultProduct().build();

        when(productService.update(1L, productDto)).thenReturn(product);

        //Result
        mockMvc.perform(put("/v1/products/1")
                        .contentType(APPLICATION_JSON)
                        .content(writeValuesAsString(productDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(writeValuesAsString(product)));
    }

    @Test
    @DisplayName("Should Save Product By Id")
    void shouldSaveProductById() throws Exception {
        //Data
        ProductDto productDto = buildDefaultProductDto().build();

        //Result
        mockMvc.perform(post("/v1/products")
                        .contentType(APPLICATION_JSON)
                        .content(writeValuesAsString(productDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
