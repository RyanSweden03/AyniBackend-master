package com.greatminds.ayni;

import com.greatminds.ayni.shopping.domain.model.aggregates.Order;
import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AyniBackendApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Test
    @Transactional
    public void testIsQualified() {
        Sale sale = new Sale("Papa", "Papa blanca", 1.0, 1L, "", 1L);
        saleRepository.save(sale);

        Order order = new Order("Descripción del pedido", 100.0, 2L, "Tarjeta de crédito", sale, 1L, 2L, new Date());

        orderRepository.save(order);

        assertFalse(order.isQualified());

        order.qualify();

        orderRepository.save(order);

        Order updatedOrder = orderRepository.findById(order.getId()).orElse(null);

        assertTrue(updatedOrder.isQualified());
    }

    @Test
    @Transactional
    public void testIsFinalized() {
        Sale sale = new Sale("Papa", "Papa blanca", 1.0, 1L, "", 1L);
        saleRepository.save(sale);

        Order order = new Order("Descripción del pedido", 100.0, 2L, "Tarjeta de crédito", sale, 1L, 2L, new Date());

        orderRepository.save(order);

        assertFalse(order.isFinalized());

        order.end();

        orderRepository.save(order);

        Order updatedOrder = orderRepository.findById(order.getId()).orElse(null);

        assertTrue(updatedOrder.isFinalized());
    }
}
