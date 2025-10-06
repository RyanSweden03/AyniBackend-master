package com.greatminds.ayni.iam.domain.services;

import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.domain.model.commands.SignInCommand;
import com.greatminds.ayni.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Long handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
