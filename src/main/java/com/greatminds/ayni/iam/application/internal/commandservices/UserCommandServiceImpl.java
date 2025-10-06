package com.greatminds.ayni.iam.application.internal.commandservices;

import com.greatminds.ayni.iam.application.internal.outboundservices.hashing.HashingService;
import com.greatminds.ayni.iam.application.internal.outboundservices.tokens.TokenService;
import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.domain.model.commands.SignInCommand;
import com.greatminds.ayni.iam.domain.model.commands.SignUpCommand;
import com.greatminds.ayni.iam.domain.model.valueobjects.EmailAddress;
import com.greatminds.ayni.iam.domain.model.valueobjects.Username;
import com.greatminds.ayni.iam.domain.services.UserCommandService;
import com.greatminds.ayni.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final HashingService hashingService;

    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Long handle(SignUpCommand command) {
        var username = new Username(command.username());
        if (userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("User with " + command.username() + " is already taken!");
        }

        var email = new EmailAddress(command.email());
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email with " + command.email() + " is already taken!" );
        }

        // Create a new User Account:
        User user = new User(
                command.username(),
                command.email(),
                hashingService.encode(command.password()),
                command.role()
        );
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(new Username(command.username()));
        if(user.isEmpty()) throw new RuntimeException("User not found");
        if(!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(new ImmutablePair<>(user.get(), token));
    }
}
