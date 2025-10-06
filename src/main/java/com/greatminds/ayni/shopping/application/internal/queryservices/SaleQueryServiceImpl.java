package com.greatminds.ayni.shopping.application.internal.queryservices;

import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import com.greatminds.ayni.shopping.domain.model.queries.GetAllSalesQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetSaleByIdQuery;
import com.greatminds.ayni.shopping.domain.services.SaleQueryService;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link SaleQueryService} interface.
 */
@Service
public class SaleQueryServiceImpl implements SaleQueryService {

    private final SaleRepository saleRepository;

    public SaleQueryServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> handle(GetAllSalesQuery query) {
        return this.saleRepository.findAll();
    }

    @Override
    public Optional<Sale> handle(GetSaleByIdQuery query) {
        return this.saleRepository.findById(query.id());
    }
}
