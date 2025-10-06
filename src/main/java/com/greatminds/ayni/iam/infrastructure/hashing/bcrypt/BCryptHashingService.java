package com.greatminds.ayni.iam.infrastructure.hashing.bcrypt;

import com.greatminds.ayni.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
