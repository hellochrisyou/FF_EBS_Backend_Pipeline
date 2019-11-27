//package com.fantasy.football.service.serviceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.fantasy.football.domain.Roster;
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.domain.entity.League;
//import com.fantasy.football.domain.entity.Player;
//import com.fantasy.football.domain.entity.Team;
//import com.fantasy.football.domain.model.Dto;
//import com.fantasy.football.repository.AccountRepository;
//import com.fantasy.football.repository.LeagueRepository;
//import com.fantasy.football.repository.PlayerRepository;
//import com.fantasy.football.service.TeamService;
//
//@Service
//public class TeamServiceImpl implements TeamService {
//
//	@Autowired
//	private LeagueRepository leagueBeanRepo;
//
//	@Autowired
//	private AccountRepository accountBeanRepo;
//
//	@Autowired
//	private PlayerRepository playerBeanRepo;
//
//	@Override
//	@Transactional
//	public boolean createTeam(final String localAcctName, final String localLeagueName, final String localTmName, final String localTmHelmet) {
//		Team newTeam = new Team();
//		Account firstAccount = this.accountBeanRepo.findByAccountName(localAcctName);
//		League newLeague = this.leagueBeanRepo.findByLeagueName(localLeagueName);
//
//		// Set Team properties
//		int leagueCount = newLeague.getCount();
//		newLeague.setCount(leagueCount + 1);
//		newTeam.setTmName(localTmName);
//		newTeam.setHelmet(localTmHelmet);
//		newTeam.setDraftNum(leagueCount);
//		if (leagueCount == 10) {
//			newLeague.setStatus("Draft");
//		}
//		// Add and set League first. Then save Account
//		firstAccount.addLeague(newLeague);
//		newLeague.addAccount(firstAccount);
//		Account persistedAccount = this.accountBeanRepo.save(firstAccount);
//
//		// Add and set Team next to Account/League-Team and Account-Team
//		persistedAccount.getLeague(leagueName).addTeam(newTeam);
//		newTeam.setLeague(persistedAccount.getLeague(leagueName));
//
//		persistedAccount.addTeam(newTeam);
//		newTeam.setAccount(persistedAccount);
//
//		this.accountBeanRepo.save(persistedAccount);
//		return true;
//	}
//
//	@Override
//	@Transactional
//	public boolean addPlayer(final String leagueName, final String teamName, final String player1Name) {
//		League repoLeague = this.leagueRepository.findByLeagueName(leagueName);
//		Roster roster = new Roster(repoLeague.getTeam(teamName).getPlayers());
//		Player newPlayer = new Player(player1Name);
//
//		if (roster.checkPosition(newPlayer.getPosition())) {
//			newPlayer.setActive(true);
//		} else {
//			newPlayer.setActive(false);
//		}
//
//		repoLeague.getTeam(teamName).addPlayer(newPlayer);
//		newPlayer.addTeam(repoLeague.getTeam(teamName));
//
//		if (repoLeague.getDraftOrder() == 10 && repoLeague.getTeam(teamName).getPlayers().size() == 16) {
//			repoLeague.setStatus("Ongoing");
//		} else {
//			repoLeague.setDraftOrder(repoLeague.getDraftOrder() + 1);
//		}
//		this.leagueRepository.save(repoLeague);
//		return true;
//	}
//
//	@Override
//	@Transactional
//	public boolean addWaiver(final String accountName, final String leagueName, final String teamName, final String player1Name, final String player2Name) {
//		Account myRepoAccount = this.accountBeanRepo.findByAccountName(accountName);
//		Player waiverPlayer = new Player(player1Name);
//		Player oldPlayer = new Player(myRepoAccount.getLeague(leagueName).getTeam(teamName).getPlayer(player2Name));
//		
//		myRepoAccount.getLeague(leagueName).getTeam(teamName).removePlayer(oldPlayer);
//		myRepoAccount.getLeague(leagueName).getTeam(teamName).addPlayer(waiverPlayer);
//		waiverPlayer.addTeam(myRepoAccount.getLeague(leagueName).getTeam(teamName));
//		
//		this.playerRepository.delete(oldPlayer);
//		this.accountBeanRepo.save(myRepoAccount);
//		return true;
//	}
//
//	@Override
//	@Transactional
//	public boolean togglePlayer(final String accountName, final String leagueName, final String teamName, final String player1Name, final String player2Name) {
//		Account myRepoAccount = this.accountBeanRepo.findByAccountName(accountName);
//		
//		myRepoAccount.getLeague(leagueName).getTeam(teamName).getPlayer(player1Name).toggleActive();
//		myRepoAccount.getLeague(leagueName).getTeam(teamName).getPlayer(player2Name).toggleActive();
//		
//		this.accountBeanRepo.save(myRepoAccount);
//		return true;
//	}
//
//	@Override
//	@Transactional
//	public boolean tradeTeam(final String accountName, final String leagueName, final String teamName, final String otherTeamName, final String player1Name, final String player2Name) {
//		Player player1 = new Player(player1Name);
//		Player player2 = new Player(player2Name);
//		Account myRepoAccount = this.accountBeanRepo.findByAccountName(accountName);
//		League myRepoLeague = this.leagueRepository.findByLeagueName(leagueName);
//
//		// Find Other Account
//		List<Account> allAccounts = new ArrayList<>(this.accountBeanRepo.findAll());
//		String otherAccountName = "";
//
//		for (Account account : allAccounts) {
//			for (Team team : account.getTeams()) {
//				if (team.getTeamName().equals(otherTeamName)) {
//					otherAccountName = accountName;
//					break;
//				}
//			}
//		}
//
//		Account otherRepoAccount = this.accountBeanRepo.findByAccountName(otherAccountName);
//
//		// Remove Players from my team and other teammyPlayer
//		Player myPlayer = new Player();
//		Player otherPlayer = new Player();
//
//		myPlayer = myRepoLeague.getTeam(teamName).getPlayer(player2Name);
//
//		otherPlayer = myRepoLeague.getTeam(otherTeamName).getPlayer(player1Name);
//
//		Player myNewPlayer = new Player(player1);
//		Player otherNewPlayer = new Player(player2);
//
//		myRepoLeague.getTeam(teamName).removePlayer(myPlayer);
//		myRepoLeague.getTeam(teamName).removePlayer(otherPlayer);
//		myRepoAccount.getLeague(leagueName).getTeam(teamName).addPlayer(myNewPlayer);
//		myNewPlayer.addTeam(myRepoAccount.getLeague(leagueName).getTeam(teamName));
//		otherRepoAccount.getLeague(leagueName).getTeam(otherTeamName).addPlayer(otherNewPlayer);
//		otherNewPlayer.addTeam(otherRepoAccount.getLeague(leagueName).getTeam(otherTeamName));
//
//		this.accountBeanRepo.save(otherRepoAccount);
//		this.playerRepository.delete(myPlayer);
//		this.accountBeanRepo.save(myRepoAccount);
//		this.playerRepository.delete(otherPlayer);
//
//		return true;
//	}
//}
