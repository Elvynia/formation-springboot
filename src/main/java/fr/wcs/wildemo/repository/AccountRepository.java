package fr.wcs.wildemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.wcs.wildemo.entity.Account;

@Repository
public interface AccountRepository
		extends JpaRepository<Account, Integer> {

	Account findOneByUsername(String username);
}
