//package com.fantasy.football.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.repository.AccountRepository;
//
//@Service
//public class MyUserDetails implements UserDetailsService {
//
//  @Autowired
//  private AccountRepository accountBeanRepo;
//
//  @Override
//  public UserDetails loadUserByUsername(String localAcctName) throws UsernameNotFoundException {
//    final Account curAccount = accountBeanRepo.findByAccountName(localAcctName);
//
//    if (curAccount == null) {
//      throw new UsernameNotFoundException("Account '" + localAcctName + "' not found");
//    }
//
//    return org.springframework.security.core.userdetails.User//
//        .withUsername(localAcctName)//
//        .password(curAccount.getAcctPass())//
//        .authorities(curAccount.getRoles())//
//        .accountExpired(false)//
//        .accountLocked(false)//
//        .credentialsExpired(false)//
//        .disabled(false)//
//        .build();
//  }
//
//}
