package com.greatminds.ayni.iam.infrastructure.authorization.sfs.services;

import com.greatminds.ayni.iam.domain.model.valueobjects.Username;
import com.greatminds.ayni.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.greatminds.ayni.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(new Username(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}
