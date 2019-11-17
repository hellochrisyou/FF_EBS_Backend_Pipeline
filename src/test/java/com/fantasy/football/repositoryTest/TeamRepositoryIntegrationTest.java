package com.fantasy.football.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fantasy.football.ServerFfApplicationTests;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.Team;
import com.fantasy.football.repository.TeamRepository;

@Transactional
public class TeamRepositoryIntegrationTest extends ServerFfApplicationTests {

	@Autowired
	private TeamRepository teamRepository;

	@Before
    public void setUp() {
		teamRepository.save(new Team("findTeamTest"));
		teamRepository.save(new Team("findAllTest1"));
		teamRepository.save(new Team("findAllTest2"));
		teamRepository.save(new Team("findAllTest3"));    
		teamRepository.save(new Team("ifTeamExistTest"));

	}	

	@Test
	public void findByTeamNameTest() {
		Team found= teamRepository.findByTeamName("findTeamTest");

		assertThat(found.getTeamName()).isEqualTo("findTeamTest");
	}
	
	@Test
	public void returnAllTeamsTest() {
		List<Team> teamArray = new ArrayList<Team>();
		
		teamArray.add(new Team("findAllTest1"));
		teamArray.add(new Team("findAllTest2"));
		teamArray.add(new Team("findAllTest3"));
		
		List<Team> testArray = teamRepository.findAll();
		
		assertThat(teamArray).isEqualTo(testArray);
	}

	@Test
	public void ifTeamExistTest() {
		assertThat(true).isEqualTo(teamRepository.existsByTeamName("ifTeamExistTest"));

	}
}
