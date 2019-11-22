//package com.fantasy.football.service.dataFetcher;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fantasy.football.cache.CachingService;
//import com.fantasy.football.domain.model.Dto;
//import com.fantasy.football.service.AccountService;
//
//import graphql.schema.DataFetcher;
//import graphql.schema.DataFetchingEnvironment;
//
//// 1st Option https://www.javainuse.com/spring/boot-jwt-mysql
//// 2nd Option https://github.com/murraco/spring-boot-jwt/blob/master/src/main/java/murraco/service/UserService.java
//@Component
//public class AuthenticateDataFetcher implements DataFetcher<String> {
//
//	@Autowired
//	private CachingService cachingService;
//
//	@Autowired
//	private AccountService accountService;
//
//	@Override
//	public String get(DataFetchingEnvironment dataFetchingEnvironment) {
//		Dto dto = dataFetchingEnvironment.getArgument("dto");		
//		return this.accountService.accountAuthenticate(dto);
//	}
//}
