package com.revature.backend.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

/**
 * Processes an {@link Authentication} request specifically for the integrated
 * {@link FirebaseAuth} API. We require a concrete class in order for Spring
 * to know what {@link AuthenticationManager} to choose since it is an interface
 *
 * @author Jay Monari
 */
public class FirebaseAuthenticationManager implements AuthenticationManager {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final FirebaseAuth firebaseAuth;

  public FirebaseAuthenticationManager(FirebaseAuth firebaseAuth) {
    this.firebaseAuth = firebaseAuth;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (authentication instanceof UnauthenticatedManager) {
      String credentials = (String) authentication.getCredentials();
      if (StringUtils.hasText(credentials)) {
        try {
          FirebaseToken token = firebaseAuth.verifyIdToken(credentials);
          return new FirebaseManager(token);
        } catch (FirebaseAuthException e) {
          throw new BadCredentialsException("Invalid Credentials");
        }
      }
    }
    logger.warn("Found unknown token type: {}", authentication);
    throw new BadCredentialsException("Invalid Credentials");
  }
}
