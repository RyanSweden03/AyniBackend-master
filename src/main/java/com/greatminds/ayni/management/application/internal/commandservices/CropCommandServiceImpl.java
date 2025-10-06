package com.greatminds.ayni.management.application.internal.commandservices;

import com.greatminds.ayni.management.domain.model.aggregates.Crop;
import com.greatminds.ayni.management.domain.model.commands.CreateCropCommand;
import com.greatminds.ayni.management.domain.model.queries.GetProductByIdQuery;
import com.greatminds.ayni.management.domain.services.CropCommandService;
import com.greatminds.ayni.management.domain.services.ProductQueryService;
import com.greatminds.ayni.management.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.stereotype.Service;

/**
 * Implementacion of {@link CropCommandService} that handles the creation of crops.
 */
@Service
public class CropCommandServiceImpl implements CropCommandService {
    private final CropRepository cropRepository;
    private final ProductQueryService productQueryService;

    public CropCommandServiceImpl(CropRepository cropRepository, ProductQueryService productQueryService) {
        this.cropRepository = cropRepository;
        this.productQueryService = productQueryService;
    }

    @Override
    public Long handle(CreateCropCommand command) {
        var getProductByIdQuery=new GetProductByIdQuery(command.productId());
        var product=productQueryService.handle(getProductByIdQuery).orElseThrow();
        var crop = new Crop(command.name(), command.pickUpWeed(), command.fertilizeCrop(), command.oxygenateCrop(),
                command.makeCropLine(), command.makeCropHole(), command.wateringDays(), command.pestCleanupDays(),
                product, command.userId());
        cropRepository.save(crop);
        return crop.getId();
    }
}
