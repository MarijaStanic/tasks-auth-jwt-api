package stanic.marija.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import stanic.marija.tasks.exception.CustomException;
import stanic.marija.tasks.model.User;
import stanic.marija.tasks.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void signup(User user) {
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			// return JWT
		} else {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
	}

}
