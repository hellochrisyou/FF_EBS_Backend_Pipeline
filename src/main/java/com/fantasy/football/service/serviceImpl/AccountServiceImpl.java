package com.fantasy.football.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.domain.model.Role;
import com.fantasy.football.exception.CustomException;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.security.JwtTokenProvider;
import com.fantasy.football.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CachingService cachingService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Transactional
	public Dto accountAuthenticate(Dto dto) {
		String accountName = dto.getMyAccountName();
		String password = dto.getPassword();

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
			this.cachingService.updateCurrentUser(accountName);
			dto.setToken(jwtTokenProvider.createToken(accountName,
					accountRepository.findByAccountName(accountName).getRoles()));
			return dto;
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username and/or password.", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	@Transactional
	public Dto register(Object oldDto) {
		Dto dto = (Dto)oldDto;
		Account newAccount = new Account();
		if (!accountRepository.existsByAccountName(dto.getMyAccountName())) {
			newAccount.setAccountName(dto.getMyAccountName());
			newAccount.setPassword(passwordEncoder.encode(dto.getPassword()));
			newAccount.addRole(Role.ROLE_CLIENT);
			accountRepository.save(newAccount);
			this.cachingService.updateCurrentUser(newAccount.getAccountName());
			dto.setToken(jwtTokenProvider.createToken(newAccount.getAccountName(), newAccount.getRoles()));
			return dto;
		} else {
			throw new CustomException("Account name is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
