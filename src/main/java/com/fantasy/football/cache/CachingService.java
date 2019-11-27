
package com.fantasy.football.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.repository.AccountRepository;

@Service
@CacheConfig(cacheNames={"user"})
public class CachingService {
	
	@Autowired
	private AccountRepository accountBeanRepo;
	
	private String cacheAcctName = "default";
	
	@Cacheable
	public Account returnCurrentUser() {
		if (this.accountBeanRepo.existsByAcctName(cacheAcctName)) {
		return this.accountBeanRepo.findByAcctName(cacheAcctName);
		} else {		
		return null;
		}
	}
	
	@CachePut
	public String updateCurrentUser(final String curAcctName) {
		this.cacheAcctName = curAcctName;
		return this.cacheAcctName;
	}
}
