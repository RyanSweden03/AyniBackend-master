package com.greatminds.ayni.management.interfaces.rest;

import com.greatminds.ayni.management.domain.model.queries.GetAllProductsQuery;
import com.greatminds.ayni.management.domain.model.queries.GetProductByIdQuery;
import com.greatminds.ayni.management.domain.services.ProductCommandService;
import com.greatminds.ayni.management.domain.services.ProductQueryService;
import com.greatminds.ayni.management.interfaces.rest.resources.CreateProductResource;
import com.greatminds.ayni.management.interfaces.rest.resources.ProductResource;
import com.greatminds.ayni.management.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.greatminds.ayni.management.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for product management.
 * Here is where the endpoints are defined.
 * This class is responsible for handling the requests and responses.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product Management Endpoints")
public class ProductController {
    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public ProductController(ProductQueryService productQueryService, ProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    /**
     * Handles the creation of a new product.
     * @param resource The product creation command.
     * @return The ID of the newly created product.
     * @throws IllegalArgumentException If there is already a product with the same name and product ID,
     *                                  or if the product ID does not exist.
     */
    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource resource){
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);

        var productId = productCommandService.handle(createProductCommand);

        if(productId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);

        if(product.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    /**
     * Handles the retrieval of all products.
     * @return A list of all products.
     */
    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductsQuery = new GetAllProductsQuery();
        var products = productQueryService.handle(getAllProductsQuery);
        var profilesResources= products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(profilesResources);
    }

    /**
     * Handles the retrieval of a product by ID.
     * @param productId The ID of the product to retrieve.
     * @return The product with the given ID.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);

        if(product.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }
}
