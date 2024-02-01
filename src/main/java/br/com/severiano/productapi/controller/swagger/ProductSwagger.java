package br.com.severiano.productapi.controller.swagger;

import br.com.severiano.productapi.dto.ProductDto;
import br.com.severiano.productapi.exception.ErrorResponse;
import br.com.severiano.productapi.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductSwagger {

    @Operation(summary = "Get all product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Product",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    ResponseEntity<List<Product>> findAll();

    @Operation(summary = "Delete product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted product",
                    content = {@Content()}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    ResponseEntity<Void> deleteById(@PathVariable Long id);

    @Operation(summary = "Get product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    ResponseEntity<Product> findById(@PathVariable Long id);

    @Operation(summary = "Create product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created product",
                    content = {@Content()}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    ResponseEntity<Void> save(@Valid @RequestBody ProductDto product);


    @Operation(summary = "Update product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated product",
                    content = {@Content(schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    ResponseEntity<Product> updateById(@PathVariable Long id, @RequestBody ProductDto product);
}
