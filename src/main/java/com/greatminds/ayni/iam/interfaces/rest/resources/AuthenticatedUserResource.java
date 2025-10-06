package com.greatminds.ayni.iam.interfaces.rest.resources;

import com.greatminds.ayni.iam.domain.model.valueobjects.Role;

import java.util.Set;

public record AuthenticatedUserResource (Long id,
                                         String username,
                                         String email,
                                         Set<Role> roles,
                                         String token) {
}
