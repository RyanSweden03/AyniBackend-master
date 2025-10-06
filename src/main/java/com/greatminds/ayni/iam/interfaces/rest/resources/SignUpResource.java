package com.greatminds.ayni.iam.interfaces.rest.resources;

public record SignUpResource(
        String username,
        String email,
        String role,
        String password
) {
}
