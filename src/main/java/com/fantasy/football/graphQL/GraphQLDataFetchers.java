package com.fantasy.football.graphQL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.service.AccountService;
import com.fantasy.football.service.AuthorizeService;
import com.fantasy.football.service.LeagueService;
import com.fantasy.football.service.TeamService;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

	@Autowired
	AccountService accountService;

	@Autowired
	LeagueService leagueService;

	@Autowired
	TeamService teamService;
	
	@Autowired
	LeagueRepository leagueRepository;

	@Autowired
	AuthorizeService authorizeService;

	public DataFetcher<League> addPlayer() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();		
			Dto dto = dataFetchingEnvironment.getArgument("dto");		
			return this.teamService.addPlayer(dto);
		};
	}

	public DataFetcher<Account> addWaiver() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();				
			Dto dto = dataFetchingEnvironment.getArgument("dto");		
			return this.teamService.addWaiver(dto);
		};
	}

	public DataFetcher<List<League>> getLeagues() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();		
			return leagueRepository.findAll();
		};
	}

	public DataFetcher<Dto> accountAuthenticate() {
		return dataFetchingEnvironment -> {
			Dto dto = dataFetchingEnvironment.getArgument("dto");		
			return this.accountService.accountAuthenticate(dto);
		};
	}

	public DataFetcher<Account> createLeague() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeAdminOnly();		
			Dto dto = dataFetchingEnvironment.getArgument("dto");		
			return this.leagueService.createLeague(dto);
		};
	}

	public DataFetcher<Account> createTeam() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();		
			Dto dto= dataFetchingEnvironment.getArgument("dto");
			return this.teamService.createTeam(dto);
		};
	}

	public DataFetcher<League> getLeague() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();		
			String leagueName= dataFetchingEnvironment.getArgument("leagueName");
			return this.leagueRepository.findByLeagueName(leagueName);
		};
	}

	public DataFetcher<Dto> register() {
		return dataFetchingEnvironment -> {
			Dto dto= dataFetchingEnvironment.getArgument("dto");
			Dto dfto = new Dto();
			return dfto;
		};
	}

	public DataFetcher<Account> togglePlayer() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();		
			Dto dto= dataFetchingEnvironment.getArgument("dto");
			return this.teamService.togglePlayer(dto);	
		};
	}

	public DataFetcher<Account> tradeTeam() {
		return dataFetchingEnvironment -> {
			this.authorizeService.authorizeBoth();		
			Dto dto= dataFetchingEnvironment.getArgument("dto");
			return this.teamService.tradeTeam(dto);
		};
	}
}
