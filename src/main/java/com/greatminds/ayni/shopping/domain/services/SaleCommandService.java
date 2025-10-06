package com.greatminds.ayni.shopping.domain.services;

import com.greatminds.ayni.shopping.domain.model.commands.CreateSaleCommand;

public interface SaleCommandService {
    Long handle(CreateSaleCommand command);

}
