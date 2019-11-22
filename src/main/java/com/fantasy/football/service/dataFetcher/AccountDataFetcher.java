//package com.fantasy.football.service.dataFetcher;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fantasy.football.cache.CachingService;
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.repository.AccountRepository;
//
//import graphql.schema.DataFetcher;
//import graphql.schema.DataFetchingEnvironment;
//
//@Component
//public class AccountDataFetcher implements DataFetcher {
//
//	@Autowired
//	private CachingService cacheingService;
//
//	@Autowired
//	private AccountRepository accountRepository;
//
//	@Override
//	@Transactional
//	public Account get(DataFetchingEnvironment dataFetchingEnvironment){
//		// NEED TO THINK IF I NEED THIS WITH NEW AUTHENTICATION NOW
//
//		Account thisAccount = new Account();
//		return this.accountRepository.save(thisAccount);
//
//	}
//}
