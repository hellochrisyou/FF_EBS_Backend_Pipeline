package com.fantasy.football.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.football.domain.Roster;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.entity.Player;
import com.fantasy.football.domain.entity.Team;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.repository.PlayerRepository;
import com.fantasy.football.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	@Transactional
	public Account createTeam(Dto dto) {
		final String accountName = dto.getMyAccountName();		
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();
		
		Team newTeam = new Team();
		Account firstAccount = this.accountRepository.findByAccountName(accountName);
		League newLeague = this.leagueRepository.findByLeagueName(leagueName);

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
		persistedAccount.getLeague(leagueName).addTeam(newTeam);
		newTeam.setLeague(persistedAccount.getLeague(leagueName));

		persistedAccount.addTeam(newTeam);
		newTeam.setAccount(persistedAccount);

		return this.accountRepository.save(persistedAccount);
	}

	@Override
	@Transactional
	public League addPlayer(Dto dto) {
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();		

		League repoLeague = this.leagueRepository.findByLeagueName(leagueName);
		Roster roster = new Roster(repoLeague.getTeam(teamName).getPlayers());
		Player newPlayer = new Player(dto.getPlayer1());

		if (roster.checkPosition(newPlayer.getPosition())) {
			newPlayer.setActive(true);
		} else {
			newPlayer.setActive(false);
		}

		repoLeague.getTeam(teamName).addPlayer(newPlayer);
		newPlayer.addTeam(repoLeague.getTeam(teamName));

		if (repoLeague.getDraftOrder() == 10 && repoLeague.getTeam(teamName).getPlayers().size() == 16) {
			repoLeague.setStatus("Ongoing");
		} else {
			repoLeague.setDraftOrder(repoLeague.getDraftOrder() + 1);
		}

		return this.leagueRepository.save(repoLeague);
	}

	@Override
	@Transactional
	public Account addWaiver(Dto dto) {
		final String accountName = dto.getMyAccountName();
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();		
		final String player2Name = dto.getPlayer2().getPlayerName();		
		
		Account myRepoAccount = this.accountRepository.findByAccountName(accountName);
		Player waiverPlayer = new Player(dto.getPlayer1());
		Player oldPlayer = new Player(myRepoAccount
										.getLeague(leagueName)
										.getTeam(teamName)
										.getPlayer(player2Name));
		myRepoAccount
			.getLeague(leagueName)
			.getTeam(teamName)
			.removePlayer(oldPlayer);

		myRepoAccount
			.getLeague(leagueName)
			.getTeam(teamName)
			.addPlayer(waiverPlayer);
		
		waiverPlayer
			.addTeam(myRepoAccount.getLeague(leagueName)
			.getTeam(teamName));

		this.playerRepository.delete(oldPlayer);
		return this.accountRepository.save(myRepoAccount);
	}

	@Override
	@Transactional
	public Account togglePlayer(Dto dto) {
		final String accountName = dto.getMyAccountName();
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();
		final String player1Name = dto.getPlayer1().getPlayerName();
		final String player2Name = dto.getPlayer2().getPlayerName();
		
		Account myRepoAccount = this.accountRepository.findByAccountName(accountName);
		
		myRepoAccount.getLeague(leagueName)
								   .getTeam(teamName)
					       			   .getPlayer(player1Name)
				       			   	   .toggleActive();
		
		myRepoAccount.getLeague(leagueName)
								   .getTeam(teamName)
						   			   .getPlayer(player2Name)
						   			   .toggleActive();
		
		return this.accountRepository.save(myRepoAccount);
	}

	@Override
	@Transactional
	public Account tradeTeam(Dto dto) {
		Player player1 = new Player(dto.getPlayer1().getPlayerName());
		Player player2 = new Player(dto.getPlayer2().getPlayerName());
		final String player1Name = dto.getPlayer1().getPlayerName();
		final String player2Name = dto.getPlayer2().getPlayerName();
		final String accountName = dto.getMyAccountName();
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();
		final String otherTeamName= dto.getOtherTeamName();

		Account myRepoAccount = this.accountRepository.findByAccountName(accountName);
		League myRepoLeague = this.leagueRepository.findByLeagueName(leagueName);
		
		// Find Other Account
		List<Account> allAccounts = new ArrayList<>(this.accountRepository.findAll());
		String otherAccountName = "";
		
		for (Account account : allAccounts) {
			for (Team team : account.getTeams()) {
				if (team.getTeamName().equals(otherTeamName)) {
					otherAccountName = accountName;
					break;
				}
			}
		}
		
		Account otherRepoAccount = this.accountRepository.findByAccountName(otherAccountName);

		// Remove Players from my team and other teammyPlayer
		Player myPlayer = new Player();
		Player otherPlayer = new Player();
		
		myPlayer = myRepoLeague
						.getTeam(teamName)
						.getPlayer(player2Name);
		
		otherPlayer = myRepoLeague	
						.getTeam(otherTeamName)
						.getPlayer(player1Name);

		Player myNewPlayer = new Player(player1);
		Player otherNewPlayer = new Player(player2);

		myRepoLeague
			.getTeam(teamName)
			.removePlayer(myPlayer);
		myRepoLeague
			.getTeam(teamName)
			.removePlayer(otherPlayer);
		myRepoAccount
			.getLeague(leagueName)
			.getTeam(teamName)
			.addPlayer(myNewPlayer);
		myNewPlayer
			.addTeam(myRepoAccount
			.getLeague(leagueName)
			.getTeam(teamName));
		otherRepoAccount
			.getLeague(leagueName)
			.getTeam(otherTeamName)
			.addPlayer(otherNewPlayer);
		otherNewPlayer
			.addTeam(otherRepoAccount
			.getLeague(leagueName)
			.getTeam(otherTeamName));

		this.accountRepository.save(otherRepoAccount);
		this.playerRepository.delete(myPlayer);
		Account returnAccount = this.accountRepository.save(myRepoAccount);
		this.playerRepository.delete(otherPlayer);

		return returnAccount;
	}
}
