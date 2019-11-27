//package com.fantasy.football.repositoryTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import javax.transaction.Transactional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fantasy.football.ServerFfApplicationTests;
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.domain.entity.League;
//import com.fantasy.football.repository.LeagueRepository;
//
//@Transactional
//public class LeagueRepositoryIntegrationTest extends ServerFfApplicationTests {
//
//	@Autowired
//	private LeagueRepository leagueRepository;
//
//	@Before
//    public void setUp() {
//		leagueRepository.save(new League("findLeagueTest"));
//		leagueRepository.save(new League("ifLeagueExistTest"));	
//
//    }
//	
//	@Test
//	public void findByLeagueNameTest() {
//		League found = leagueRepository.findByLeagueName("findLeagueTest");
//
//		assertThat(found.getLeagueName()).isEqualTo("findLeagueTest");
//	}
//
//	@Test
//	public void ifLeagueExistTest() {
//		assertThat(true).isEqualTo(leagueRepository.existsByLeagueName("ifLeagueExistTest"));
//
//	}
//}
