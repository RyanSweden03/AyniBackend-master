package com.greatminds.ayni.shopping.domain.services;

import com.greatminds.ayni.shopping.domain.model.commands.CreateRateCommand;

public interface RateCommandService {
    Long handle(CreateRateCommand command);
}
