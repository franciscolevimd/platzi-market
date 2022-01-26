package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Product operations.")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "This path operation retrieves all products in the application.", tags = {"Product"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retreives all products",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(  implementation = Product.class  )))
                    }),
    })
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "This operation path retrieves a specific product data.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Required product data",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json")
                    })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "Id of product to be searched", example = "23")
            @PathVariable(value = "id") int productId) {
        return productService.getProduct(productId).map(
                product -> new ResponseEntity<>(product, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "This operation path retrieves all the products belong to a specific category.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All products belonging to the category",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(  implementation = Product.class )))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = {
                            @Content(mediaType = "application/json")
                    })
    })
    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "Id of category to be searched", example = "2")
            @PathVariable("category_id") int categoryId) {
        return productService.getByCategory(categoryId).map(
                products -> new ResponseEntity<>(products, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "This operation path save a new product data.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New product data.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))
                    }),
    })
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @Operation(summary = "This operation path delete a product of the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Product data has been removed.",
                    content = {
                            @Content(mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json")
                    })
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Id of product to be deleted.", example = "51")
            @PathVariable("id") int productId) {
        return productService.delete(productId) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
