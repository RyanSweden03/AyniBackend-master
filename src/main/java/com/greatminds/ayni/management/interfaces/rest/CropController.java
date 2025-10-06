package com.greatminds.ayni.management.interfaces.rest;

import com.greatminds.ayni.management.domain.model.queries.GetAllCropsQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByIdQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByProductIdQuery;
import com.greatminds.ayni.management.domain.services.CropCommandService;
import com.greatminds.ayni.management.domain.services.CropQueryService;
import com.greatminds.ayni.management.interfaces.rest.resources.CreateCropResource;
import com.greatminds.ayni.management.interfaces.rest.resources.CropResource;
import com.greatminds.ayni.management.interfaces.rest.transform.CreateCropCommandFromResourceAssembler;
import com.greatminds.ayni.management.interfaces.rest.transform.CropResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling crop.
 * Here is where the endpoints are defined.
 * This class is responsible for handling the requests and responses.
 * It is the entry point for the crop management.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/crops", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Crops", description = "Crop Management Endpoints")
public class CropController {
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    public CropController(CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }

    /**
     * Handles the creation of a new crop.
     * @param resource The crop creation command.
     * @return The ID of the newly created crop.
     */
    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource){
        var createCropCommand = CreateCropCommandFromResourceAssembler.toCommandFromResource(resource);

        var cropId = cropCommandService.handle(createCropCommand);

        if(cropId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getCropIdByQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropIdByQuery);

        if(crop.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(crop.get());
        return new ResponseEntity<>(cropResource, HttpStatus.CREATED);
    }

    /**
     * Handles the retrieval of all crops.
     * @return A list of all crops.
     */
    @GetMapping
    public ResponseEntity<List<CropResource>> getAllCrops() {
        var getAllCropsQuery = new GetAllCropsQuery();
        var crops = cropQueryService.handle(getAllCropsQuery);
        var profilesResources= crops.stream().map(CropResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(profilesResources);
    }

    /**
     * Handles the retrieval of a crop by its ID.
     * @param cropId The ID of the crop to retrieve.
     * @return The crop with the given ID.
     */
    @GetMapping("/{cropId}")
    public ResponseEntity<CropResource> getCropById(@PathVariable Long cropId) {
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);

        if(crop.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }
}
