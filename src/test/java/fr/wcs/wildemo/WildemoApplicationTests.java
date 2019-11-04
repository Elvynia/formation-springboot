package fr.wcs.wildemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.wcs.wildemo.entity.Account;
import fr.wcs.wildemo.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WildemoApplicationTests {

	@Autowired
	private AccountService service;

	@Test
	public void contextLoads() {
		Account newAccount = this.service.create("jmasson", "jmasson");
		Assert.assertEquals("jmasson", newAccount.getUsername());
		Assert.assertNotNull(newAccount.getId());
	}

}
