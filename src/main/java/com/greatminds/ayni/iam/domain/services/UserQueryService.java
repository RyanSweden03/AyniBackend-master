package com.greatminds.ayni.iam.domain.services;


import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.domain.model.queries.GetAllUsersQuery;
import com.greatminds.ayni.iam.domain.model.queries.GetUserByIdQuery;
import com.greatminds.ayni.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;


public interface UserQueryService {
    Optional<User> handle(GetUserByUsernameQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    List<User> handle(GetAllUsersQuery query);
}
