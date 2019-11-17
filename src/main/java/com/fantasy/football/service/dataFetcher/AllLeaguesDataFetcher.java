package com.fantasy.football.service.dataFetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.League;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.service.AuthorizeService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllLeaguesDataFetcher implements DataFetcher<List<League>> {
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private LeagueRepository leagueRepository;

	@Override
    public List<League> get(DataFetchingEnvironment dataFetchingEnvironment) {		
		this.authorizeService.authorizeBoth();		
		return leagueRepository.findAll();
    }
}
