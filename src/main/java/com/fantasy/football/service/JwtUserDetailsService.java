package com.fantasy.football.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.repository.AccountRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {

		Account account = this.accountRepository.findByAccountName(accountName);
		if (account == null) {
			return null;
		}
		return new org.springframework.security.core.userdetails.User(account.getAccountName(), account.getPassword(),
				new ArrayList<>());
	}

	public Account save(String accountName, String password) {
		Account newAccount = new Account();
		newAccount.setAccountName(accountName);
		newAccount.setPassword(bcryptEncoder.encode(password));
		return accountRepository.save(newAccount);
	}
}