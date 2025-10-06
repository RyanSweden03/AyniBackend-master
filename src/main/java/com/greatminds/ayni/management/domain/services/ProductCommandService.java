package com.greatminds.ayni.management.domain.services;

import com.greatminds.ayni.management.domain.model.commands.CreateProductCommand;

/**
 * Service for handling product commands.
 */
public interface ProductCommandService {
    Long handle(CreateProductCommand command);
}
