package com.fantasy.football.serviceImplTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.Roster;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.entity.Player;
import com.fantasy.football.domain.entity.Team;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.repository.PlayerRepository;
import com.fantasy.football.service.LeagueService;
import com.fantasy.football.service.TeamService;
import com.fantasy.football.service.serviceImpl.TeamServiceImpl;

@RunWith(SpringRunner.class)
public class TeamServiceImplIntegrationTest {

	@TestConfiguration
	static class TeamServiceImplTestContextConfiguration {
		@Bean
		public TeamService teamService() {
			return new TeamServiceImpl();
		}
	}

	@Autowired
	private TeamService teamService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private CachingService cachingService;

	@MockBean
	private LeagueRepository leagueRepository;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private PlayerRepository playerRepository;

	@Before
	public void setUp() {
		this.accountRepository.save(new Account("Account Name"));
		this.cachingService.updateCurrentUser("Account Name");
	}

	@Test
	public void createTeamTest() {
		Dto dto = new Dto();
		dto.setMyAccountName("Account Name");
		dto.setMyLeagueName("League Name");
		dto.setMyTeamName("Team Name");
		dto.setMyTeamHelmet("Red");
		this.leagueService.createLeague(dto);
		assertThat(this.teamService.createTeam(dto)).isNot(null);
	}

	@Test
	public void addPlayerTest() {
		Dto dto = new Dto();
		dto.setMyAccountName("Account Name");
		dto.setMyLeagueName("League Name");
		dto.setMyTeamName("Team Name");
		dto.setMyTeamHelmet("Red");
		this.leagueService.createLeague(dto);
		assertThat(this.teamService.createTeam(dto)).isNot(null);
	}

	@Override
	@Transactional
	public League addPlayer(Dto dto) {
		League repoLeague = new League();
		Player newPlayer = new Player(dto.getPlayer1());

		repoLeague = this.leagueRepository.findByLeagueName(dto.getMyLeagueName());
		Roster roster = new Roster(repoLeague.getTeam(dto.getMyTeamName()).getPlayers());

		if (roster.checkPosition(newPlayer.getPosition())) {
			newPlayer.setActive(true);
		} else {
			newPlayer.setActive(false);
		}

		repoLeague.getTeam(dto.getMyTeamName()).addPlayer(newPlayer);
		newPlayer.addTeam(repoLeague.getTeam(dto.getMyTeamName()));

		if (repoLeague.getDraftOrder() == 10 && repoLeague.getTeam(dto.getMyTeamName()).getPlayers().size() == 16) {
			repoLeague.setStatus("Ongoing");
		} else {
			repoLeague.setDraftOrder(repoLeague.getDraftOrder() + 1);
		}

		return this.leagueRepository.save(repoLeague);
	}

	@Override
	public Account addWaiver(Dto dto) {
		final String accountName = dto.getMyAccountName();
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();
		final String player2Name = dto.getPlayer2().getPlayerName();
		Player waiverPlayer = new Player();
		Player oldPlayer = new Player();

		waiverPlayer = dto.getPlayer1();
		Account myRepoAccount = this.accountRepository.findByAccountName(accountName);

		oldPlayer = myRepoAccount.getLeague(leagueName).getTeam(teamName).getPlayer(player2Name);
		myRepoAccount.getLeague(leagueName).getTeam(teamName).removePlayer(oldPlayer);
		myRepoAccount.getLeague(leagueName).getTeam(teamName).addPlayer(waiverPlayer);
		waiverPlayer.addTeam(myRepoAccount.getLeague(leagueName).getTeam(teamName));

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

		myRepoAccount.getLeague(leagueName).getTeam(teamName).getPlayer(player1Name).toggleActive();

		myRepoAccount.getLeague(leagueName).getTeam(teamName).getPlayer(player2Name).toggleActive();

		return this.accountRepository.save(myRepoAccount);
	}

	@Override
	@Transactional
	public Account tradeTeam(Dto dto) {
		final String accountName = dto.getMyAccountName();
		final String leagueName = dto.getMyLeagueName();
		final String teamName = dto.getMyTeamName();
		final String otherTeamName = dto.getOtherTeamName();
		
		Account myRepoAccount = this.accountRepository.findByAccountName(accountName);
		League myRepoLeague = this.leagueRepository.findByLeagueName(leagueName);
		
		// Find Other Account
		List<Account> allAccounts = new ArrayList<Account>(this.accountRepository.findAll());
		String otherAccountName = "";
		
		for (Account account : allAccounts) {
			for (Team team : account.getTeams()) {
				if (team.getTeamName().equals(otherTeamName)) {
					otherAccountName = account.getAccountName();
					break;
				}
			}
		}
		Account otherRepoAccount = new Account();
		otherRepoAccount = this.accountRepository.findByAccountName(otherAccountName);

		// Remove Players from my team and other teammyPlayer
		Player myPlayer = new Player();
		Player otherPlayer = new Player();
		myPlayer = myRepoLeague.getTeam(teamName).getPlayer(player2Name);
		otherPlayer = myRepoLeague.getTeam(otherTeamName).getPlayer(player1Name);

		Player myNewPlayer = new Player(dto.getPlayer1());
		Player otherNewPlayer = new Player(dto.getPlayer2());

		myRepoLeague.getTeam(teamName).removePlayer(myPlayer);
		myRepoLeague.getTeam(otherTeamName).removePlayer(otherPlayer);

		myRepoAccount.getLeague(leagueName).getTeam(teamName).addPlayer(myNewPlayer);
		myNewPlayer.addTeam(myRepoAccount.getLeague(leagueName).getTeam(teamName));
		otherRepoAccount.getLeague(leagueName).getTeam(otherTeamName).addPlayer(otherNewPlayer);
		otherNewPlayer.addTeam(otherRepoAccount.getLeague(leagueName).getTeam(otherTeamName));

		this.accountRepository.save(otherRepoAccount);
		this.playerRepository.delete(myPlayer);
		Account returnAccount = this.accountRepository.save(myRepoAccount);
		this.playerRepository.delete(otherPlayer);

		return returnAccount;
	}
}
