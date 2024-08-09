package com.revature.backend.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Inside of the {@link JwtAuthenticationFilter} we begin filtering our request
 * and if their is an Authorization header we can pass this specialized token
 * into our {@link FirebaseAuthenticationManager} to verify.
 * @author Jay Monari
 */
public class UnauthenticatedManager
      extends UsernamePasswordAuthenticationToken {
  private static final long serialVersionUID = 8957908813414540861L;

  public UnauthenticatedManager(String bearerToken) {
    super(null, bearerToken);
  }
}
