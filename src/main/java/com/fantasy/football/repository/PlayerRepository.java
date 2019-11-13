package com.fantasy.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fantasy.football.domain.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	@Override
	List<Player> findAll();

	Player findByPlayerName(String name);

	boolean existsByPlayerName(String playerName);
}
