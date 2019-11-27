//package test.java.com.fantasy.football.serviceImplTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fantasy.football.cache.CachingService;
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.domain.model.Dto;
//import com.fantasy.football.repository.AccountRepository;
//import com.fantasy.football.repository.LeagueRepository;
//import com.fantasy.football.repository.PlayerRepository;
//import com.fantasy.football.service.LeagueService;
//import com.fantasy.football.service.TeamService;
//import com.fantasy.football.service.serviceImpl.TeamServiceImpl;
//
//@RunWith(SpringRunner.class)
//public class TeamServiceImplIntegrationTest {
//
//	@TestConfiguration
//	static class TeamServiceImplTestContextConfiguration {
//		@Bean
//		public TeamService teamService() {
//			return new TeamServiceImpl();
//		}
//	}
//
//	@Autowired
//	private TeamService teamService;
//
//	@Autowired
//	private LeagueService leagueService;
//
//	@Autowired
//	private CachingService cachingService;
//
//	@MockBean
//	private LeagueRepository leagueRepository;
//
//	@MockBean
//	private AccountRepository accountRepository;
//
//	@MockBean
//	private PlayerRepository playerRepository;
//
//	@Before
//	public void setUp() {
//		Dto dto = new Dto();
//		// Account Creation
//		this.accountRepository.save(new Account("Account Name"));
//		this.cachingService.updateCurrentUser("Account Name");
//		// League Creation
//		this.leagueService.createLeague(dto);
//		// Team Creation
//		this.teamService.createTeam(dto);
//	}
//
//	@Test
//	public void createTeamTest() {
//		Dto dto = new Dto();
//		this.leagueService.createLeague(dto);
//		assertThat(this.teamService.createTeam(dto)).isNot(null);
//	}
//
//	@Test
//	public void addPlayerTest() {
//		Dto dto = new Dto();
//		assertThat(this.teamService.addPlayer(dto)).isNot(null);
//	}
//
//	@Test
//	public void addWaiverTest() {
//		Dto dto = new Dto();
//		assertThat(this.teamService.addWaiver(dto)).isNot(null);
//	}
//
//	@Test
//	public void togglePlayerTest() {
//		Dto dto = new Dto();
//		assertThat(this.teamService.togglePlayer(dto)).isNot(null);
//	}
//
//	@Test
//	public void tradeTeamTest() {
//		Dto dto = new Dto();
//		assertThat(this.teamService.tradeTeam(dto)).isNot(null);
//	}
//}