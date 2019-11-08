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
public class CreateLeagueDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CachingService cacheingService;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		League league= dataFetchingEnvironment.getArgument("league");
		Account myAccount = new Account();
		myAccount = this.cacheingService.returnCurrentUser();
		
		League newLeague = new League(league.getName());
		myAccount.addLeague(newLeague);
		return this.accountRepository.save(myAccount);
	}
}
