package fr.wcs.wildemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.wcs.wildemo.entity.Account;
import fr.wcs.wildemo.repository.AccountRepository;

@Service
public class AccountService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Account create(String username, String rawPassword) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(this.encoder.encode(rawPassword));
		try {
			account = this.repo.save(account);
			LOGGER.debug("Nouveau compte créé avec nom d'utilisateur '{}'.", username);
		} catch (DataAccessException e) {
			LOGGER.error("Impossible de créer un nouveau compte utilisateur :", e);
		}
		return account;
	}
}
