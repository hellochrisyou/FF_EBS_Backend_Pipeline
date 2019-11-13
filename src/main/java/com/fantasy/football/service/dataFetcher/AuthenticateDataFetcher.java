package com.fantasy.football.service.dataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.exception.CustomException;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.security.JwtTokenProvider;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

// 1st Option https://www.javainuse.com/spring/boot-jwt-mysql
// 2nd Option https://github.com/murraco/spring-boot-jwt/blob/master/src/main/java/murraco/service/UserService.java
@Component
public class AuthenticateDataFetcher implements DataFetcher<String> {

	@Autowired
	private CachingService cachingService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
		Account thisAccount = dataFetchingEnvironment.getArgument("account");
		
		String accountName = thisAccount.getAccountName();
		String password = thisAccount.getPassword();

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
			this.cachingService.updateCurrentUser(accountName);
			return jwtTokenProvider.createToken(accountName, accountRepository.findByAccountName(accountName).getRoles());
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username and/or password.", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
