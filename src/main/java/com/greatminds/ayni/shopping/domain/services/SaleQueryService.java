package com.greatminds.ayni.shopping.domain.services;

import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import com.greatminds.ayni.shopping.domain.model.queries.GetAllSalesQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetSaleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SaleQueryService {
    List<Sale> handle(GetAllSalesQuery query);
    Optional<Sale> handle(GetSaleByIdQuery query);
}
