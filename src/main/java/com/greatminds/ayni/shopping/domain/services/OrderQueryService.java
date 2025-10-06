package com.greatminds.ayni.shopping.domain.services;

import com.greatminds.ayni.shopping.domain.model.aggregates.Order;
import com.greatminds.ayni.shopping.domain.model.queries.GetAllOrdersQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetOrderByIdQuery;
import com.greatminds.ayni.shopping.domain.model.queries.GetOrderBySaleIdQuery;

import java.util.List;
import java.util.Optional;

public interface OrderQueryService {
    Optional<Order> handle(GetOrderByIdQuery query);

    Optional<Order> handle(GetOrderBySaleIdQuery query);

    List<Order> handle(GetAllOrdersQuery query);
}