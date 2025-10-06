package com.greatminds.ayni.iam.infrastructure.tokens.jwt;

import com.greatminds.ayni.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {
    String generateToken(Authentication authentication);
    String getBearerTokenFrom(HttpServletRequest request);
}
