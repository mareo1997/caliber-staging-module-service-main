package com.revature.backend.security;

import com.google.firebase.auth.FirebaseToken;

/**
 * This has been left with a specific implementation in mind, for Firebase. This
 * could have easily just been given to an {@link AuthenticatedManager} instead
 * but this allows for clarity if any other Token classes may want to extend the
 * more generalized {@link AuthenticatedManager} class.
 * @author Jay Monari
 */
public class FirebaseManager extends AuthenticatedManager {
  private static final long serialVersionUID = 5679872493310689712L;

  public FirebaseManager(FirebaseToken token) {
    super(token.getClaims());
  }
}
