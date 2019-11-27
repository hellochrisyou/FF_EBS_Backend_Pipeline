//package com.fantasy.football.repositoryTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.fantasy.football.ServerFfApplicationTests;
//import com.fantasy.football.domain.entity.Account;
//import com.fantasy.football.repository.AccountRepository;
//
//@Transactional
//public class AccountRepositoryIntegrationTest extends ServerFfApplicationTests {
//
//	@Autowired
//	private AccountRepository accountRepository;
//	
//	@Before
//    public void setUp() {
//		accountRepository.save(new Account("findAccountTest"));
//		accountRepository.save(new Account("findAllTest1"));
//		accountRepository.save(new Account("findAllTest2"));
//		accountRepository.save(new Account("findAllTest3"));
//		accountRepository.save(new Account("ifAccountExistTest"));		
//    }
//
//	@Test
//	public void findByAccountNameTest() {
//		Account found = accountRepository.findByAccountName("findAccountTest");
//
//		assertThat(found.getAccountName()).isEqualTo("findLeagueTest");
//	}
//
//	@Test
//	public void findAllAccountsTest() {
//		List<Account> accountArray = new ArrayList<Account>();
//		
//		accountArray.add(new Account("findAllTest1"));
//		accountArray.add(new Account("findAllTest2"));
//		accountArray.add(new Account("findAllTest3"));
//		
//		List<Account> testArray = accountRepository.findAll();
//		
//		assertThat(accountArray).isEqualTo(testArray);
//	}
//
//	@Test
//	public void ifAccountExistTest() {
//		assertThat(true).isEqualTo(accountRepository.existsByAccountName("ifAccountExistTest"));
//	}
//}
