package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.service.AuthorizeService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateLeagueDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CachingService cacheingService;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		
		this.authorizeService.authorizeAdminOnly();
		
		Dto dto = dataFetchingEnvironment.getArgument("dto");
		Account myAccount = this.accountRepository.findByAccountName(this.cacheingService.returnCurrentUser());
		
		League newLeague = new League(dto.getMyLeagueName());
		myAccount.addLeague(newLeague);
		return this.accountRepository.save(myAccount);
	}
}
