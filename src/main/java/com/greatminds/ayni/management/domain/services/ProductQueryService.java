package com.greatminds.ayni.management.domain.services;

import com.greatminds.ayni.management.domain.model.entities.Product;
import com.greatminds.ayni.management.domain.model.queries.GetAllProductsQuery;
import com.greatminds.ayni.management.domain.model.queries.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service for handling product queries.
 */
public interface ProductQueryService {
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsQuery query);
}
