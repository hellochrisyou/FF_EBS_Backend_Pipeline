//package com.fantasy.football.service;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.repository.AccountRepository;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private AccountRepository accountBeanRepo;
//
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
//
//	@Override
//	public UserDetails loadUserByUsername(String localAcctName) throws UsernameNotFoundException {
//		Account curAccount = this.accountBeanRepo.findByAccountName(localAcctName);
//		if (curAccount == null) {
//			return null;
//		}
//		return new org.springframework.security.core.userdetails.User(curAccount.getAcctName(), curAccount.getAcctPass(),
//				new ArrayList<>());
//	}
//
//	public Account save(String localAcctName, String localAcctPass) {
//		Account newAccount = new Account();
//		newAccount.setAcctName(localAcctName)
//		newAccount.setAcctPass(bcryptEncoder.encode(localAcctPass));
//		return accountBeanRepo.save(newAccount);
//	}
//}