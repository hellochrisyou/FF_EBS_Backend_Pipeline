package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.dao.entity.Account;
import com.fantasy.football.dao.repository.AccountRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AccountDataFetcher implements DataFetcher<Account> {	
	
	@Autowired
	private CachingService cacheingService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
	public Account get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
		// NEED TO THINK IF I NEED THIS WITH NEW AUTHENTICATION NOW
		
//		String name= dataFetchingEnvironment.getArgument("accountName");
		Account thisAccount = new Account();
//		
//		if (this.accountRepository.existsByAccountName(name)) {
//			thisAccount = this.accountRepository.findByAccountName(name);
//			this.cacheingService.updateCurrentUser(thisAccount.getAccountName());
//			return thisAccount;
//		} else {
//			thisAccount = new Account(name);
//			this.cacheingService.updateCurrentUser(thisAccount.getAccountName());
			return this.accountRepository.save(thisAccount);
//		}
	}	
}
