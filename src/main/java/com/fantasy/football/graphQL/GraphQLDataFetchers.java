package com.fantasy.football.graphQL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.service.AccountService;
//import com.fantasy.football.service.AuthorizeService;
import com.fantasy.football.service.LeagueService;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

	@Autowired
	AccountService acctBeanService;

//	@Autowired
//	LeagueService leagueBeanService;

//	@Autowired
//	TeamService tmBeanService;
	
//	@Autowired
//	LeagueRepository leagueBeanRepo;

//	@Autowired
//	AuthorizeService authorizeBeanService;

//	public DataFetcher<League> addPlayer() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();		
//			Dto dto = dataFetchingEnvironment.getArgument("dto");		
//			return this.tmBeanService.addPlayer(dto);
//		};
//	}
//
//	public DataFetcher<Account> addWaiver() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();				
//			Dto dto = dataFetchingEnvironment.getArgument("dto");		
//			return this.tmBeanService.addWaiver(dto);
//		};
//	}
//
//	public DataFetcher<List<League>> getLeagues() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();		
//			return leagueBeanRepo.findAll();
//		};
//	}
//
//	public DataFetcher<Dto> acctAuthenticate() {
//		return dataFetchingEnvironment -> {
//			Dto dto = dataFetchingEnvironment.getArgument("dto");		
//			return this.acctBeanService.acctAuthenticate(dto);
//		};
//	}
//
//	public DataFetcher<Account> createLeague() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeAdminOnly();		
//			Dto dto = dataFetchingEnvironment.getArgument("dto");		
//			return this.leagueBeanService.createLeague(dto);
//		};
//	}
//
//	public DataFetcher<Account> createTeam() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();		
//			Dto dto= dataFetchingEnvironment.getArgument("dto");
//			return this.tmBeanService.createTeam(dto);
//		};
//	}
//
//	public DataFetcher<League> getLeague() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();		
//			String leagueName= dataFetchingEnvironment.getArgument("leagueName");
//			return this.leagueBeanRepo.findByLeagueName(leagueName);
//		};
//	}
//
	public DataFetcher<String> registerAcct() {
		return dataFetchingEnvironment -> {
			Map<String, Object> test = dataFetchingEnvironment.getVariables();
			acctBeanService.registerAcct("hello");
			return "test";
		};
	}
//
//	public DataFetcher<Account> togglePlayer() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();		
//			Dto dto= dataFetchingEnvironment.getArgument("dto");
//			return this.tmBeanService.togglePlayer(dto);	
//		};
//	}
//
//	public DataFetcher<Account> tradeTeam() {
//		return dataFetchingEnvironment -> {
//			this.authorizeBeanService.authorizeBoth();		
//			Dto dto= dataFetchingEnvironment.getArgument("dto");
//			return this.tmBeanService.tradeTeam(dto);
//		};
//	}
}
