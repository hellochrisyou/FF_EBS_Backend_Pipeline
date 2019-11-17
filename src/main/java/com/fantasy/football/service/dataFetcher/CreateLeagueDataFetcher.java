package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.service.AuthorizeService;
import com.fantasy.football.service.LeagueService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateLeagueDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private LeagueService leagueServuce;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {		
		this.authorizeService.authorizeAdminOnly();		
		Dto dto = dataFetchingEnvironment.getArgument("dto");		
		return this.leagueServuce.createLeague(dto);
	}
}
