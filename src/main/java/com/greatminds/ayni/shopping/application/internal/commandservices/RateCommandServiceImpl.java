package com.greatminds.ayni.shopping.application.internal.commandservices;

import com.greatminds.ayni.shopping.domain.model.aggregates.Rate;
import com.greatminds.ayni.shopping.domain.model.commands.CreateRateCommand;
import com.greatminds.ayni.shopping.domain.model.queries.GetSaleByIdQuery;
import com.greatminds.ayni.shopping.domain.services.RateCommandService;
import com.greatminds.ayni.shopping.domain.services.SaleQueryService;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.RateRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link RateCommandService} interface.
 */
@Service
public class RateCommandServiceImpl implements RateCommandService {
    private final RateRepository rateRepository;
    private final SaleQueryService saleQueryService;

    public RateCommandServiceImpl(RateRepository rateRepository, SaleQueryService saleQueryService) {
        this.rateRepository = rateRepository;
        this.saleQueryService = saleQueryService;
    }

    @Override
    public Long handle(CreateRateCommand command) {
        var getSaleByIdQuery = new GetSaleByIdQuery(command.productId());
        var sale = saleQueryService.handle(getSaleByIdQuery).orElseThrow();
        var rate = new Rate(command.rate(), command.date(), sale, command.userId());
        rateRepository.save(rate);
        return rate.getId();
    }
}
