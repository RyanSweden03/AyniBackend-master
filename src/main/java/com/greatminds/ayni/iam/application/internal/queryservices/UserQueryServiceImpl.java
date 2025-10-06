package com.greatminds.ayni.iam.application.internal.queryservices;

import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.domain.model.queries.GetAllUsersQuery;
import com.greatminds.ayni.iam.domain.model.queries.GetUserByIdQuery;
import com.greatminds.ayni.iam.domain.model.queries.GetUserByUsernameQuery;
import com.greatminds.ayni.iam.domain.services.UserQueryService;
import com.greatminds.ayni.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return this.userRepository.findByUsername(query.username());
    }
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return this.userRepository.findById(query.id());
    }
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

}
