package com.greatminds.ayni.iam.interfaces.rest.transform;

import com.greatminds.ayni.iam.domain.model.commands.SignUpCommand;
import com.greatminds.ayni.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource){
        return new SignUpCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                resource.role()
        );
    }
}
