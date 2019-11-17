package com.fantasy.football.service;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.model.Dto;

public interface LeagueService {
	Account createLeague(Dto dto);
}