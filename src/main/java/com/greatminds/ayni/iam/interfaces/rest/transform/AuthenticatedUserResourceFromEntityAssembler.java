package com.greatminds.ayni.iam.interfaces.rest.transform;

import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token){
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), user.getEmail(), user.getRoles(), token);
    }
}
