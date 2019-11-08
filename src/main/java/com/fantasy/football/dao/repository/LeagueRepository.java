package com.fantasy.football.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fantasy.football.dao.entity.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

	boolean existsByName(String name);

	League findByName(String name);
	
}
