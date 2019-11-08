package com.fantasy.football.service.dataFetcher;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.dao.entity.Account;
import com.fantasy.football.dao.entity.League;
import com.fantasy.football.dao.entity.Player;
import com.fantasy.football.dao.entity.Team;
import com.fantasy.football.dao.repository.AccountRepository;
import com.fantasy.football.dao.repository.LeagueRepository;
import com.fantasy.football.dao.repository.PlayerRepository;
import com.fantasy.football.domain.Dto;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class TradeTeamDataFetcher implements DataFetcher<Account> {
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	@Transactional
    public Account get(DataFetchingEnvironment dataFetchingEnvironment) {
		Dto dto= dataFetchingEnvironment.getArgument("dto");

		Account myRepoAccount = new Account();
		League myRepoLeague = new League();
		List<Account> allAccounts = new ArrayList<Account>();
		
		myRepoAccount = this.accountRepository.findByName(dto.getMyAccountName());
		myRepoLeague = this.leagueRepository.findByName(dto.getMyLeagueName());
		// Find Other Account 
		allAccounts = this.accountRepository.findAll();
		String otherAccountName = "";
		for (Account account: allAccounts) {
			for (Team team: account.getTeams()) {
				if (team.getName().equals(dto.getOtherTeamName())) {
					otherAccountName = account.getName();
					break;
				}
			}
		}		
		Account otherRepoAccount = new Account();
		otherRepoAccount = this.accountRepository.findByName(otherAccountName);
		
		// Remove Players from my team and other teammyPlayer
		Player myPlayer = new Player();
		Player otherPlayer = new Player();
		myPlayer = myRepoLeague.getTeam(dto.getMyTeamName()).getPlayer(dto.getPlayer2().getName());		
		otherPlayer = myRepoLeague.getTeam(dto.getOtherTeamName()).getPlayer(dto.getPlayer1().getName());
		
		Player myNewPlayer = new Player(dto.getPlayer1());
		Player otherNewPlayer = new Player(dto.getPlayer2());
		
		myRepoLeague.getTeam(dto.getMyTeamName()).removePlayer(myPlayer);
		myRepoLeague.getTeam(dto.getOtherTeamName()).removePlayer(otherPlayer);
		
		myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()).addPlayer(myNewPlayer);		
		myNewPlayer.addTeam(myRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getMyTeamName()));		
		otherRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getOtherTeamName()).addPlayer(otherNewPlayer);
		otherNewPlayer.addTeam(otherRepoAccount.getLeague(dto.getMyLeagueName()).getTeam(dto.getOtherTeamName()));
		
		this.accountRepository.save(otherRepoAccount);
		this.playerRepository.delete(myPlayer);
		Account returnAccount = this.accountRepository.save(myRepoAccount);		
		this.playerRepository.delete(otherPlayer);
		
		return returnAccount;		
	}
}
