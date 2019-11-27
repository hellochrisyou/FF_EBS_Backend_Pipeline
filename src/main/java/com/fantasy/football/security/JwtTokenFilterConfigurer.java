//package com.fantasy.football.security;
//
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//  private JwtTokenProvider jwtTokenProvider;
//
//  public JwtTokenFilterConfigurer(final JwtTokenProvider localJwtTokenProvider) {
//    this.jwtTokenProvider = localJwtTokenProvider;
//  }
//
//  @Override
//  public void configure(HttpSecurity localHttp) throws Exception {
//    JwtTokenFilter curCustomFilter = new JwtTokenFilter(jwtTokenProvider);
//    localHttp.addFilterBefore(curCustomFilter, UsernamePasswordAuthenticationFilter.class);
//  }
//
//}
