package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.Player;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.repository.PlayerRepository;
import com.fantasy.football.service.AuthorizeService;
import com.fantasy.football.domain.model.Dto;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AddWaiverDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		
		this.authorizeService.authorizeBoth();
		
		Dto dto= dataFetchingEnvironment.getArgument("dto");

		Account myRepoAccount = new Account();
		Player waiverPlayer = new Player();
		Player oldPlayer = new Player();
		
		waiverPlayer = dto.getPlayer1();
		myRepoAccount = this.accountRepository.findByAccountName(dto.getMyAccountName());				
		
		oldPlayer = myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()).getPlayer(dto.getPlayer2().getPlayerName());		
		myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()).removePlayer(oldPlayer);
		
		myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()).addPlayer(waiverPlayer);
		waiverPlayer.addTeam(myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()));
		
		this.playerRepository.delete(oldPlayer);
		return this.accountRepository.save(myRepoAccount);
	}
}
