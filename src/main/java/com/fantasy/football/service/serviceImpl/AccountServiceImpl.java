package com.fantasy.football.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.repository.AccountRepository;
//import com.fantasy.football.security.JwtTokenProvider;
import com.fantasy.football.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

//	@Autowired
//	private AccountRepository accountBeanRepo;
//
//	@Autowired
//	private CachingService cacheingBeanService;

//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private AuthenticationManager authenticationManager;

//	@Override
//	@Transactional
//	public Dto acctAuthenticate(final String localAcctName, final String localAcctPass) {
//		try {
//			Dto returnDto = new Dto();
//			returnDto.setAcctName(localAcctName);
//			returnDto.setAcctPass(localAcctPass);			
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(localAcctName, localAcctPass));
//			this.cachingService.updateCurrentUser(localAcctName);			
//			returnDto.setToken(jwtTokenProvider.createToken(localAcctName,
//					accountBeanRepo.findByAccountName(localAcctName).getRoles()));			
//			return returnDto;
//		} catch (AuthenticationException e) {
//			throw new CustomException("Invalid username and/or password.", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}

	@Override
	@Transactional
	public String registerAcct( String test) {
		test = "hhh";
		return "testAcctServiceImpl";
	}
//	public Dto register(final String accountName, final String accountPassword) {
//		Account newAccount = new Account();
//		if (!accountBeanRepo.existsByAccountName(accountName)) {
//			Dto returnDto = new Dto();
//			returnDto.setMyAccountName(accountName);
//			returnDto.setAccountPassword(accountPassword);
//			newAccount.setAccountName(returnDto.getMyAccountName());
//			newAccount.setPassword(passwordEncoder.encode(accountPassword));
//			newAccount.addRole(Role.ROLE_CLIENT);
//			accountBeanRepo.save(newAccount);
//			this.cachingService.updateCurrentUser(newAccount.getAccountName());
//			returnDto.setToken(jwtTokenProvider.createToken(newAccount.getAccountName(), newAccount.getRoles()));
//			return returnDto;
//		} else {
//			throw new CustomException("Account name is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}

}
