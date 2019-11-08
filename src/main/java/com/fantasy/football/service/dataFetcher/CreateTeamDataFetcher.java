package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.dao.entity.Account;
import com.fantasy.football.dao.entity.League;
import com.fantasy.football.dao.entity.Team;
import com.fantasy.football.dao.repository.AccountRepository;
import com.fantasy.football.dao.repository.LeagueRepository;
import com.fantasy.football.domain.Dto;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateTeamDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		Dto dto= dataFetchingEnvironment.getArgument("dto");

		Team newTeam = new Team();
		
		Account firstAccount = this.accountRepository.findByName(dto.getMyAccountName());
 		League newLeague = this.leagueRepository.findByName(dto.getMyLeagueName());
		
 		// Set Team properties
 		int leagueCount = newLeague.getCount();
 		newLeague.setCount(leagueCount + 1);
 		newTeam.setName(dto.getMyTeamName());
		newTeam.setHelmet(dto.getMyTeamHelmet());
		newTeam.setMatchup_id(leagueCount);
		newTeam.setDraft_position(leagueCount);
		if (leagueCount == 10) {
			newTeam.setMatchup(1);
			newLeague.setStatus("Draft");
		} else {
			newTeam.setMatchup(leagueCount+1);
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
