package com.fantasy.football.serviceImplTest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fantasy.football.cache.CachingService;
import com.fantasy.football.domain.entity.Account;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.domain.model.Role;
import com.fantasy.football.exception.CustomException;
import com.fantasy.football.repository.AccountRepository;
import com.fantasy.football.service.AccountService;
import com.fantasy.football.service.serviceImpl.AccountServiceImpl;

@RunWith(SpringRunner.class)
public class AccountServiceImplIntegrationTest {

	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {
		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountRepository accountRepository;

	@Before
	public void setUp() {
		
	}

	@Test
    public void registerOrAuthenticateTest() {
		Dto dto = new Dto();
		dto.setMyAccountName("Account Name");
		dto.setPassword("Password");
		accountService.register(dto);
		
		assertThat(this.accountService.authenticate(dto)).asInstanceOf(InstanceOfAssertFactories.STRING);
    }
}

//@Override
//@Transactional
//public Account findAccount(String accountName) {
//		cacheingService.updateCurrentUser(accountName);
//		Account thisAccount = new Account();
//	if (this.accountRepository.existsByAccountName(accountName)) {
//		thisAccount = this.accountRepository.findByAccountName(accountName);
//		return thisAccount;
//	} else {
//		thisAccount = new Account(accountName);		
//		return this.accountRepository.save(thisAccount);
//	}
//}
