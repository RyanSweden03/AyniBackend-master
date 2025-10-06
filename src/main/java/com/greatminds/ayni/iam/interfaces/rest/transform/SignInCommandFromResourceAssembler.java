package com.greatminds.ayni.iam.interfaces.rest.transform;

import com.greatminds.ayni.iam.domain.model.commands.SignInCommand;
import com.greatminds.ayni.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource){
        return new SignInCommand(
                resource.username(),
                resource.password()
        );
    }
}
