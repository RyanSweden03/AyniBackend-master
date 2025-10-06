package com.greatminds.ayni.shopping.interfaces.rest;

import com.greatminds.ayni.shopping.domain.model.queries.GetOrderBySaleIdQuery;
import com.greatminds.ayni.shopping.domain.services.OrderQueryService;
import com.greatminds.ayni.shopping.interfaces.rest.resources.OrderResource;
import com.greatminds.ayni.shopping.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/sales/{saleId}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sales")
public class SaleOrdersController {
    private final OrderQueryService orderQueryService;

    public SaleOrdersController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResource> getOrderBySaleIdAndOrderId(@PathVariable Long saleId, @PathVariable Long orderId){
        var getOrderBySaleId = new GetOrderBySaleIdQuery(saleId, orderId);
        var order = orderQueryService.handle(getOrderBySaleId);

        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());
        return ResponseEntity.ok(orderResource);
    }
}
