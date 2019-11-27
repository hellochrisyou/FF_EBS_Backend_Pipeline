//package test.java.com.fantasy.football.serviceImplTest;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fantasy.football.repository.AccountRepository;
//import com.fantasy.football.service.AccountService;
//import com.fantasy.football.service.serviceImpl.AccountServiceImpl;
//
//@RunWith(SpringRunner.class)
//public class AccountServiceImplIntegrationTest {
//
//	@TestConfiguration
//	static class AccountServiceImplTestContextConfiguration {
//		@Bean
//		public AccountService accountService() {
//			return new AccountServiceImpl();
//		}
//	}
//
//	@Autowired
//	private AccountService accountService;
//
//	@MockBean
//	private AccountRepository accountRepository;
//
//	@Before
//	public void setUp() {
//		
//	}
//
////	@Test
////    public void registerOrAuthenticateTest() {
////		Dto dto = new Dto();
////		dto.setMyAccountName("Account Name");
////		dto.setPassword("Password");
////		accountService.register(dto);
////		
////		assertThat(this.accountService.accountAuthenticate(dto)).asInstanceOf(InstanceOfAssertFactories.STRING);
////    }
//}
//
////@Override
////@Transactional
////public Account findAccount(String accountName) {
////		cacheingService.updateCurrentUser(accountName);
////		Account thisAccount = new Account();
////	if (this.accountRepository.existsByAccountName(accountName)) {
////		thisAccount = this.accountRepository.findByAccountName(accountName);
////		return thisAccount;
////	} else {
////		thisAccount = new Account(accountName);		
////		return this.accountRepository.save(thisAccount);
////	}
////}
