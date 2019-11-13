package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.service.AuthorizeService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class LeagueDataFetcher implements DataFetcher<League> {	
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Override
	@Transactional
	public League get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
		
		this.authorizeService.authorizeBoth();
		
		String leagueName= dataFetchingEnvironment.getArgument("leagueName");			
		
		return this.leagueRepository.findByLeagueName(leagueName);
	}	
}
