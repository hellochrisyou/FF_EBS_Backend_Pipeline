package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.entity.Team;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.service.AuthorizeService;
import com.fantasy.football.domain.model.Dto;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateTeamDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		
		this.authorizeService.authorizeBoth();
		
		Dto dto= dataFetchingEnvironment.getArgument("dto");

		Team newTeam = new Team();
		
		Account firstAccount = this.accountRepository.findByAccountName(dto.getMyAccountName());
 		League newLeague = this.leagueRepository.findByLeagueName(dto.getMyLeagueName());
		
 		// Set Team properties
 		int leagueCount = newLeague.getCount();
 		newLeague.setCount(leagueCount + 1);
 		newTeam.setTeamName(dto.getMyTeamName());
		newTeam.setHelmet(dto.getMyTeamHelmet());
		newTeam.setDraftPosition(leagueCount);
		if (leagueCount == 10) {
			newLeague.setStatus("Draft");
		} 
		// Add and set League first. Then save Account
		firstAccount.addLeague(newLeague);
		newLeague.addAccount(firstAccount);
		Account persistedAccount = this.accountRepository.save(firstAccount);		
		
		// Add and set Team next to Account/League-Team and Account-Team		
		persistedAccount.getLeague(dto.getMyLeagueName()).addTeam(newTeam);
		newTeam.setLeague(persistedAccount.getLeague(dto.getMyLeagueName()));		
		
		persistedAccount.addTeam(newTeam);
		newTeam.setAccount(persistedAccount);
		
		return this.accountRepository.save(persistedAccount);
	}
}
