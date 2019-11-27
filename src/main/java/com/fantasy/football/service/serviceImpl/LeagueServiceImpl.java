package com.fantasy.football.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.service.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private AccountRepository accountBeanRepo;

	@Autowired
	private CachingService cacheingBeanService;

	@Override
	@Transactional
	public Account createLeague(final String localLeagueName) {
		// Retrieve current account name via cache
		Account localAcount = this.cacheingBeanService.returnCurrentUser();
		if (localAcount != null) {
			League newLeague = new League(localLeagueName);
			localAcount.addLeague(newLeague);
			return this.accountBeanRepo.save(localAcount);
		} else {
			return null;
		}
	}
}
