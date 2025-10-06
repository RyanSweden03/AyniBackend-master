package com.greatminds.ayni.shopping.application.internal.queryservices;

import com.greatminds.ayni.shopping.domain.model.aggregates.Rate;
import com.greatminds.ayni.shopping.domain.model.queries.GetAllRatesQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetRateByIdQuery;
import com.greatminds.ayni.shopping.domain.services.RateQueryService;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.RateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link RateQueryService} interface.
 */
@Service
public class RateQueryServiceImpl implements RateQueryService {
    private final RateRepository rateRepository;

    public RateQueryServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> handle(GetAllRatesQuery query) {
        return rateRepository.findAll();
    }

    @Override
    public Optional<Rate> handle(GetRateByIdQuery query) {
        return rateRepository.findById(query.id());
    }
}
