package com.greatminds.ayni.iam.interfaces.rest.transform;

import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRoles()
        );
    }
}
