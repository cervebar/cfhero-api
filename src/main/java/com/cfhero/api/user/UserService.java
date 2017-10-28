package com.cfhero.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * XXX TODO create javadoc
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public User getUser(String id) {
		return repo.findById(Long.parseLong(id));
	}

}
