package com.fantasy.football.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.repository.AccountRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
    final Account account = accountRepository.findByAccountName(accountName);

    if (account == null) {
      throw new UsernameNotFoundException("Account '" + accountName + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(accountName)//
        .password(account.getPassword())//
        .authorities(account.getRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
