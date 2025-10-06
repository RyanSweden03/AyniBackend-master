package com.greatminds.ayni.management.interfaces.rest;

import com.greatminds.ayni.management.domain.model.queries.GetAllCropsByProductIdQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByProductIdQuery;
import com.greatminds.ayni.management.domain.services.CropQueryService;
import com.greatminds.ayni.management.interfaces.rest.resources.CropResource;
import com.greatminds.ayni.management.interfaces.rest.transform.CropResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling crops by product.
 * Here is where the endpoints are defined.
 * This class is responsible for handling the requests and responses.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/products/{productId}/crops", produces= MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Products")
public class ProductCropsController {

    private final CropQueryService cropQueryService;

    public ProductCropsController(CropQueryService cropQueryService) {
        this.cropQueryService = cropQueryService;
    }

    /**
     * Handles the creation of a new crop.
     * @param productId The product ID.
     * @return The ID of the newly created crop.
     */
    @GetMapping
    public ResponseEntity<List<CropResource>> getAllCropsByProductId(@PathVariable Long productId) {
        var getAllCropsByProductIdQuery = new GetAllCropsByProductIdQuery(productId);
        var crops = cropQueryService.handle(getAllCropsByProductIdQuery);
        var cropsResource= crops.stream().map(CropResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(cropsResource);
    }

    /**
     * Handles the creation of a new crop.
     * @param productId The product ID.
     * @param cropId The crop ID.
     * @return The ID of the newly created crop.
     */
    @GetMapping("/{cropId}")
    public ResponseEntity<CropResource> getCropByProductIdAndCropId(@PathVariable Long productId, @PathVariable Long cropId) {
        var getCropByProductId = new GetCropByProductIdQuery(productId, cropId);
        var crop = cropQueryService.handle(getCropByProductId);

        if(crop.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }
}
