package com.greatminds.ayni.iam.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.iam.domain.model.aggregates.User;
import com.greatminds.ayni.iam.domain.model.valueobjects.EmailAddress;
import com.greatminds.ayni.iam.domain.model.valueobjects.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(Username username);
    Boolean existsByUsername(Username username);
    Boolean existsByEmail(EmailAddress emailAddress);
}
