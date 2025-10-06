package com.greatminds.ayni.shopping.interfaces.rest;

import com.greatminds.ayni.shopping.domain.model.queries.GetAllSalesQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetSaleByIdQuery;
import com.greatminds.ayni.shopping.domain.services.SaleCommandService;
import com.greatminds.ayni.shopping.domain.services.SaleQueryService;
import com.greatminds.ayni.shopping.interfaces.rest.resources.CreateSaleResource;
import com.greatminds.ayni.shopping.interfaces.rest.resources.SaleResource;
import com.greatminds.ayni.shopping.interfaces.rest.transform.CreateSaleCommandFromResourceAssembler;
import com.greatminds.ayni.shopping.interfaces.rest.transform.SaleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for sales management.
 * Here is where the endpoints are defined.
 * This class is responsible for handling the requests and responses.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/sales", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sales", description = "Sales Management Endpoints")
public class SaleController {
    private final SaleQueryService saleQueryService;
    private final SaleCommandService saleCommandService;

    public SaleController(SaleQueryService saleQueryService, SaleCommandService saleCommandService) {
        this.saleQueryService = saleQueryService;
        this.saleCommandService = saleCommandService;
    }

    /**
     * Handles the creation of a new sale.
     * @param resource The sale creation command.
     * @return The ID of the newly created sale.
     */
    @PostMapping
    public ResponseEntity<SaleResource> createSale(@RequestBody CreateSaleResource resource){
        var createSaleCommand = CreateSaleCommandFromResourceAssembler.toCommandFromResource(resource);

        var saleId = saleCommandService.handle(createSaleCommand);

        if(saleId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getSaleIdByQuery = new GetSaleByIdQuery(saleId);
        var sale = saleQueryService.handle(getSaleIdByQuery);

        if(sale.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var saleResource = SaleResourceFromEntityAssembler.toResourceFromEntity(sale.get());
        return new ResponseEntity<>(saleResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SaleResource>> getAllSales() {
        var getAllSalesQuery = new GetAllSalesQuery();
        var sales = saleQueryService.handle(getAllSalesQuery);
        var salesResources= sales.stream().map(SaleResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(salesResources);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleResource> getSaleById(@PathVariable Long saleId) {
        var getSaleByIdQuery = new GetSaleByIdQuery(saleId);
        var sale = saleQueryService.handle(getSaleByIdQuery);
        if(sale.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var saleResource = SaleResourceFromEntityAssembler.toResourceFromEntity(sale.get());
        return ResponseEntity.ok(saleResource);
    }
}
