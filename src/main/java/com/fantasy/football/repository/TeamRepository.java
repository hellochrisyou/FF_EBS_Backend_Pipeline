package com.fantasy.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fantasy.football.domain.entity.Team;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {	
	@Override
	List<Team> findAll();

	Team findByTeamName(String name);

	boolean existsByTeamName(String teamName);
}