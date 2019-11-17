package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.service.AccountService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

// For User operations https://github.com/murraco/spring-boot-jwt/blob/master/src/main/java/murraco/service/UserService.java
@Component
public class RegisterDataFetcher implements DataFetcher<String> {

	@Autowired
	private CachingService cachingService;

	@Autowired
	private AccountService accountService;

	@Override
	@Transactional
	public String get(DataFetchingEnvironment dataFetchingEnvironment) {		
		Dto dto = dataFetchingEnvironment.getArgument("dto");		
		return this.accountService.register(dto);
	}
}
