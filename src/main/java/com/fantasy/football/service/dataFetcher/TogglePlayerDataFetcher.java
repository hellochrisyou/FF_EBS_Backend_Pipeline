package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.service.AuthorizeService;
import com.fantasy.football.domain.model.Dto;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class TogglePlayerDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		
		this.authorizeService.authorizeBoth();
		
		Dto dto= dataFetchingEnvironment.getArgument("dto");

		Account myRepoAccount = new Account();
		myRepoAccount = this.accountRepository.findByAccountName(dto.getMyAccountName());				
		myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()).getPlayer(dto.getPlayer1().getPlayerName()).toggleActive();
		myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()).getPlayer(dto.getPlayer2().getPlayerName()).toggleActive();
		return this.accountRepository.save(myRepoAccount);
	}
}
