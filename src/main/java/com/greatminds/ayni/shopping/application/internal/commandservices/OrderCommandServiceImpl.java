package com.greatminds.ayni.shopping.application.internal.commandservices;

import com.greatminds.ayni.shopping.domain.model.commands.CreateOrderCommand;
import com.greatminds.ayni.shopping.domain.model.commands.FinalizeOrderCommand;
import com.greatminds.ayni.shopping.domain.model.commands.QualifyOrderCommand;
import com.greatminds.ayni.shopping.domain.model.queries.GetSaleByIdQuery;
import com.greatminds.ayni.shopping.domain.services.SaleQueryService;
import com.greatminds.ayni.shopping.interfaces.rest.resources.UpdateOrderResource;
import com.greatminds.ayni.shopping.domain.model.aggregates.Order;
import com.greatminds.ayni.shopping.domain.services.OrderCommandService;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation of the {@link OrderCommandService} interface.
 * This class handles the creation and update of orders.
 */
@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    private final SaleQueryService saleQueryService;
    public OrderCommandServiceImpl(OrderRepository orderRepository, SaleQueryService saleQueryService) {
        this.orderRepository = orderRepository;
        this.saleQueryService = saleQueryService;
    }

    @Override
    public Long handle(CreateOrderCommand command) {
        var getSaleByIdQuery = new GetSaleByIdQuery(command.saleId());
        var sale = saleQueryService.handle(getSaleByIdQuery).orElseThrow();
        Date currentDate = new Date();
        var order = new Order(command.description(), command.totalPrice(), command.quantity(), command.paymentMethod(), sale, command.orderedBy(), command.acceptedBy(), currentDate);
        order.updateDate(currentDate);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public Long handle(FinalizeOrderCommand command) {
        orderRepository.findById(command.orderId())
                .map(order -> {
                    order.end();
                    orderRepository.save(order);
                    return order.getId();
                }).orElseThrow(() -> new RuntimeException("Order not found"));
        return null;
    }

    @Override
    public Long handle(QualifyOrderCommand command) {
        orderRepository.findById(command.orderId())
                .map(order -> {
                    order.qualify();
                    orderRepository.save(order);
                    return order.getId();
                }).orElseThrow(() -> new RuntimeException("Order not found"));
        return null;
    }

    @Override
    public Long deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found");
        }
        orderRepository.deleteById(orderId);
        return orderId;
    }

    @Override
    public Long updateOrder(Long orderId, UpdateOrderResource request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " not found"));
        if(order.getStatus().equals("pending")) {
            var getSaleByIdQuery = new GetSaleByIdQuery(request.saleId());
            var sale = saleQueryService.handle(getSaleByIdQuery).orElseThrow();
            Date currentDate = new Date();
            order.update(new Order(request.description(), request.totalPrice(), request.quantity(), request.paymentMethod(), sale, request.orderedBy(), request.acceptedBy(), currentDate));
            order.updateDate(currentDate);
            orderRepository.save(order);
            return order.getId();
        } else {
            throw new IllegalArgumentException("Only pending orders can be updated");
        }
    }
}