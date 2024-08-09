package com.revature.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * One of the first filters that a request must pass in order to reach our
 * actual controller layer. The filter is specifically for Firebase JSON Web
 * Tokens and the authorization of them through the instanstiated
 * {@link com.google.firebase.auth.FirebaseAuth}
 * @author Jay Monari
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  /**
   * This filter is meant only for requests involving the backend and not the
   * actuator so we filter for any requests that are directed towards it and
   * only process requests with an Authorization header it is passed into the
   * {@link FirebaseManager} to authenticate the JWT and then is turned into a
   * tangible {@link UsernamePasswordAuthenticationToken} that we can set inside
   * of our {@link SecurityContextHolder}
   *
   * @see SecurityContextHolder#getContext()
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    final String uri = request.getRequestURI();
    if (uri.contains("actuator")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.replace("Bearer ", "");
      Authentication authentication =
          authenticationManager.authenticate(new UnauthenticatedManager(token));

      UsernamePasswordAuthenticationToken firebaseAuthToken =
          new UsernamePasswordAuthenticationToken(
              authentication.getName(),
              "",
              AuthorityUtils.createAuthorityList("ROLE_USER")
          );
      firebaseAuthToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
      );
      SecurityContextHolder.getContext().setAuthentication(firebaseAuthToken);
    }

    filterChain.doFilter(request, response);
  }
}
