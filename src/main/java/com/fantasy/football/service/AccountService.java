package com.fantasy.football.service;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.model.Dto;

public interface AccountService {
	String authenticate(Dto dto);
	String register(Dto dto);
//	Account findAccount(String name);
}
