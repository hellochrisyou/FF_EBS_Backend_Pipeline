package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.dao.entity.Account;
import com.fantasy.football.dao.entity.League;
import com.fantasy.football.dao.repository.AccountRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AuthenticateUserDataFetcher implements DataFetcher<String>{
	
	@Autowired
	private CachingService cacheingService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
    public String get(DataFetchingEnvironment dataFetchingEnvironment) {
		String email = dataFetchingEnvironment.getArgument("email");
		String password = dataFetchingEnvironment.getArgument("password");
		
		if (this.accountRepository.existsByAccountName(email)) {
			Account myAccount = this.accountRepository.findByAccountName(email);
			if (myAccount.getPassword().equals(password)) {
				this.cacheingService.updateCurrentUser(myAccount.getAccountName());
				return "Success";
			} else {
				return "Failed";
			}
		} else {
			return "DNE";
		}
	}
}
