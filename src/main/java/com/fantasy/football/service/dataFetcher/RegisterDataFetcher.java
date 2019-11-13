package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.exception.CustomException;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.security.JwtTokenProvider;
import com.fantasy.football.service.JwtUserDetailsService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

// For User operations https://github.com/murraco/spring-boot-jwt/blob/master/src/main/java/murraco/service/UserService.java
@Component
public class RegisterDataFetcher implements DataFetcher<String> {

	@Autowired
	private CachingService cachingService;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	@Transactional
	public String get(DataFetchingEnvironment dataFetchingEnvironment) {		
		Account newAccount = new Account(dataFetchingEnvironment.getArgument("account"));
				
		if (!accountRepository.existsByAccountName(newAccount.getAccountName())) {
			newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
			accountRepository.save(newAccount);
			this.cachingService.updateCurrentUser(newAccount.getAccountName());
			return jwtTokenProvider.createToken(newAccount.getAccountName(), newAccount.getRoles());
		} else {
			throw new CustomException("Account name is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
