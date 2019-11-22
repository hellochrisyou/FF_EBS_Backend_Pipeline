package com.fantasy.football.service;

import com.fantasy.football.domain.model.Dto;

public interface AccountService {
	Dto accountAuthenticate(Dto dto);
	Dto register(Object dto);
}
