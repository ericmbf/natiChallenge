package org.nati.grade.services;

import java.util.List;

import javax.annotation.Resource;

import org.nati.grade.domain.User;
import org.nati.grade.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	public User findById(Integer id) {
		return userRepository.findById(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}
}
