package stanic.marija.tasks.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stanic.marija.tasks.dto.UserDataDTO;
import stanic.marija.tasks.model.JwtTokenRequest;
import stanic.marija.tasks.model.JwtTokenResponse;
import stanic.marija.tasks.model.User;

@CrossOrigin()
@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private stanic.marija.tasks.service.UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody JwtTokenRequest authenticationRequest) {
		String token = userService.signin(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		return ResponseEntity.ok(new JwtTokenResponse(token));
	}

	@PostMapping("/signup")
	public String signup(@RequestBody UserDataDTO user) {
		return userService.signup(modelMapper.map(user, User.class));
	}

}