package fr.wcs.wildemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.wcs.wildemo.repository.AccountRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private AccountRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return this.repo.findOneByUsername(username);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
