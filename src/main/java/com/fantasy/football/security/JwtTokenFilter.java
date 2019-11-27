//package com.fantasy.football.security;
//
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.fantasy.football.exception.CustomException;
//
//// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//  private JwtTokenProvider jwtTokenProvider;
//
//  public JwtTokenFilter(JwtTokenProvider localJwtTokenProvider) {
//    this.jwtTokenProvider = localJwtTokenProvider;
//  }
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest localHttpServletRequest, HttpServletResponse localHttpServletResponse, FilterChain localFilterChain) throws ServletException, IOException {
//    String localToken = jwtTokenProvider.resolveToken(localHttpServletRequest);
//    try {
//      if (localToken != null && jwtTokenProvider.validateToken(localToken)) {
//        Authentication localAuth = jwtTokenProvider.getAuthentication(localToken);
//        SecurityContextHolder.getContext().setAuthentication(localAuth);
//      }
//    } catch (CustomException ex) {
//      //this is very important, since it guarantees the user is not authenticated at all
//      SecurityContextHolder.clearContext();
//      localHttpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
//      return;
//    }
//
//    localFilterChain.doFilter(localHttpServletRequest, localHttpServletResponse);
//  }
//
//}
