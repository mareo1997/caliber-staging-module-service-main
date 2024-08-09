package com.revature.backend.testing.security;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.backend.security.JwtAuthenticationFilter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
/**
 * Tests the JwtAuthenticaitionFilter Class. It contains 1 test.<p>
 * Test 1: Tests that JwtAuthenticaitionFilter can be instantiated<p>
 * Unable to test further as class has 1 method that has no returns or clear ways to indicate success or failure of the method<p>
 * @author Matthew Sheldon
 */
@SpringBootTest
public class JwtAuthenticationFilterTest {
  @Mock
  private AuthenticationManager mockAuthMan;

  @InjectMocks
  private JwtAuthenticationFilter jwtAuth;

  @BeforeEach
  public void before(){
    

  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest(){
    assertThat(jwtAuth).isNotNull();;
  }
  
}
