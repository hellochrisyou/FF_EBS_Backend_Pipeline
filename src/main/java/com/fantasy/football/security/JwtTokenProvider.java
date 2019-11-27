//package com.fantasy.football.security;
//
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.fantasy.football.domain.model.Role;
//import com.fantasy.football.exception.CustomException;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenProvider {
//
//  /**
//   * For simplicity, this is a static key here. Ideally, in a
//   * microservices environment, this key would be kept in a config-server.
//   */
//  @Value("${security.jwt.token.secret-key:secret-key}")
//  private String secretKey;
//
//  @Value("${security.jwt.token.expire-length:3600000}")
//  private long validityInMilliseconds = 3600000; // 1h
//
//  @Autowired
//  private MyUserDetails myUserDetailsBean;
//
//  @PostConstruct
//  protected void init() {
//    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//  }
//
//  public String createToken(String localAcctName, List<Role> roles) {
//
//    Claims localClaims = Jwts.claims().setSubject(localAcctName);
//    localClaims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
//
//    Date now = new Date();
//    Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//    return Jwts.builder()
//        .setClaims(localClaims)
//        .setIssuedAt(now)
//        .setExpiration(validity)
//        .signWith(SignatureAlgorithm.HS256, secretKey)
//        .compact();
//  }
//
//  public Authentication getAuthentication(final String localToken) {
//    UserDetails curUserDetails = myUserDetailsBean.loadUserByUsername(getUsername(localToken));
//    return new UsernamePasswordAuthenticationToken(curUserDetails, "", curUserDetails.getAuthorities());
//  }
//
//  public String getUsername(String localToken) {
//    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(localToken).getBody().getSubject();
//  }
//
//  public String resolveToken(final HttpServletRequest localReq) {
//    String localBearerToken = localReq.getHeader("Authorization");
//    if (localBearerToken != null && localBearerToken.startsWith("Bearer ")) {
//      return localBearerToken.substring(7);
//    }
//    return null;
//  }
//
//  public boolean validateToken(final String localToken) {
//    try {
//      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(localToken);
//      return true;
//    } catch (JwtException | IllegalArgumentException e) {
//      throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//}
