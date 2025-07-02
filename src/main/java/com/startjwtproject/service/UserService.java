package com.startjwtproject.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.startjwtproject.model.User;
import com.startjwtproject.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	public Boolean delete(UUID id) {

		try {
			User u = (User) userRepository.findById("id_user", id);
			return userRepository.deletar(u);
		} catch (Exception e) {
			return false;
		}
	}

}
