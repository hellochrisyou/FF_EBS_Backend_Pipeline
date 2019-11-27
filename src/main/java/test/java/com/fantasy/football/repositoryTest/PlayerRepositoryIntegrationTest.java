//package com.fantasy.football.repositoryTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.fantasy.football.ServerFfApplicationTests;
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.domain.entity.Player;
//import com.fantasy.football.repository.PlayerRepository;
//
//@Transactional
//public class PlayerRepositoryIntegrationTest extends ServerFfApplicationTests {
//
//	@Autowired
//	private PlayerRepository playerRepository;
//
//	@Before
//    public void setUp() {
//		playerRepository.save(new Player("findAllTest1"));
//		playerRepository.save(new Player("findAllTest2"));
//		playerRepository.save(new Player("findAllTest3"));
//    }
//
//	@Test
//	public void findAllPlayersTest() {
//		List<Player> playerArray = new ArrayList<Player>();
//		
//		playerArray.add(new Player("findAllTest1"));
//		playerArray.add(new Player("findAllTest1"));
//		playerArray.add(new Player("findAllTest1"));
//
//		List<Player> testArray = playerRepository.findAll();
//
//		assertThat(playerArray).isEqualTo(testArray);
//	}
//}
