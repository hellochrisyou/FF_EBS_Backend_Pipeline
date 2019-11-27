//package test.java.com.fantasy.football.serviceImplTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import javax.transaction.Transactional;
//
//import org.assertj.core.api.InstanceOfAssertFactories;
//import org.junit.Before;
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
//import com.fantasy.football.domain.entity.League;
//import com.fantasy.football.domain.model.Dto;
//import com.fantasy.football.repository.AccountRepository;
//import com.fantasy.football.service.AccountService;
//import com.fantasy.football.service.LeagueService;
//import com.fantasy.football.service.serviceImpl.LeagueServiceImpl;
//
//@RunWith(SpringRunner.class)
//public class LeagueServiceImplIntegrationTest {
//	
//	@TestConfiguration
//	static class LeagueServiceImplTestContextConfiguration {
//	    @Bean
//	    public LeagueService leagueService() {
//	        return new LeagueServiceImpl();
//	    }
//	}
//	
//	@Autowired
//	private LeagueService leagueService;	
//	@Autowired
//	private CachingService cachingService;
//	
//	@MockBean
//	private AccountRepository accountRepository;
//	
//	@Before
//    public void setUp() {
//		// Creates User
//		this.accountRepository.save(new Account("Account Name"));
//		this.cachingService.updateCurrentUser("Account Name");
//		// Create League
//		Dto dto = new Dto();
//		dto.setMyLeagueName("League Name");
//		this.leagueService.createLeague(dto);
//	}
//	
//	@Test
//	public void createLeagueTest() {
//		Dto dto = new Dto();
//		dto.setMyLeagueName("League Name");
//		assertThat(this.leagueService.createLeague(dto)).isNot(null);
//	}	
//	
//	@Test
//	public void addWaiverTest() {
//		
//	}
//}
