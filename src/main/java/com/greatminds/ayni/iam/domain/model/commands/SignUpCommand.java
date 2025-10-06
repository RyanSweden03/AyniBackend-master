package com.greatminds.ayni.iam.domain.model.commands;

public record SignUpCommand(
        String username,
        String email,
        String password,
        String role
) {
}
