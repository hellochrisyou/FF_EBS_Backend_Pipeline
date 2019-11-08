package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.dao.entity.Account;
import com.fantasy.football.dao.repository.AccountRepository;
import com.fantasy.football.dao.repository.LeagueRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AccountDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Override
	@Transactional
	public Account get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {		
		String name= dataFetchingEnvironment.getArgument("name");
		Account thisAccount = new Account();
		
		if (this.accountRepository.existsByName(name)) {
			thisAccount = this.accountRepository.findByName(name);
			return thisAccount;
		} else {
			thisAccount = new Account(name);		
			return this.accountRepository.save(thisAccount);
		}
	}	
}
