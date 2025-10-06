package com.greatminds.ayni.shopping.application.internal.commandservices;

import com.greatminds.ayni.shopping.domain.model.commands.CreateSaleCommand;
import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import com.greatminds.ayni.shopping.domain.services.SaleCommandService;
import com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories.SaleRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link SaleCommandService} interface.
 */
@Service
public class SaleCommandServiceImpl implements SaleCommandService {

    private final SaleRepository saleRepository;

    public SaleCommandServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Long handle(CreateSaleCommand command) {
        var sale = new Sale(command.name(), command.description(), command.unitPrice(), command.quantity(), command.imageUrl(), command.userId());
        saleRepository.save(sale);
        return sale.getId();
    }
}
