package com.greatminds.ayni.shopping.domain.services;

import com.greatminds.ayni.shopping.domain.model.aggregates.Rate;
import com.greatminds.ayni.shopping.domain.model.queries.GetAllRatesQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetRateByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RateQueryService {
    List<Rate> handle(GetAllRatesQuery query);

    Optional<Rate> handle(GetRateByIdQuery query);
}
