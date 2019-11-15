package com.fantasy.football.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fantasy.football.security.JwtTokenFilterConfigurer;
import com.fantasy.football.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Disable CSRF (cross site request forgery)
    http.csrf().disable();

    // No session will be created or used by spring security
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Entry points
    http.authorizeRequests()//
    	.antMatchers("/graphql").permitAll()
    	.antMatchers("/vendor/**").permitAll()
    	.antMatchers("/graphiql").permitAll()
    	.antMatchers("/login").permitAll()        
        .anyRequest().authenticated()
	    .and()
	    .formLogin();
    
    http.exceptionHandling().accessDeniedPage("/login");
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth
//          .inMemoryAuthentication()
//              .passwordEncoder(passwordEncoder())
//              .withUser("mi3o").password("{noop}nbusr123").roles("USER").and()
//              .withUser("admin").password("{noop}nbusr123").roles("USER", "ADMIN");
//  }
  }

  // Singleton for password/thread safety?
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }
}
