package com.fantasy.football.service.dataFetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.dao.entity.League;
import com.fantasy.football.dao.repository.LeagueRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllLeaguesDataFetcher implements DataFetcher<List<League>> {
	@Autowired
	private LeagueRepository leagueRepository;

	@Override
    public List<League> get(DataFetchingEnvironment dataFetchingEnvironment) {
		return leagueRepository.findAll();
    }
}
