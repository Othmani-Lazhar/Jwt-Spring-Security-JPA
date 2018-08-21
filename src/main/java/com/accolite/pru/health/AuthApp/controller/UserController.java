package com.accolite.pru.health.AuthApp.controller;

import com.accolite.pru.health.AuthApp.exception.UserLoginException;
import com.accolite.pru.health.AuthApp.exception.UserRegistrationException;
import com.accolite.pru.health.AuthApp.model.User;
import com.accolite.pru.health.AuthApp.model.payload.ApiResponse;
import com.accolite.pru.health.AuthApp.model.payload.JwtAuthenticationResponse;
import com.accolite.pru.health.AuthApp.model.payload.LoginRequest;
import com.accolite.pru.health.AuthApp.model.payload.RegistrationRequest;
import com.accolite.pru.health.AuthApp.security.JwtTokenProvider;
import com.accolite.pru.health.AuthApp.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Logger logger;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Optional<Authentication> authenticationOpt = userService.authenticateUser(loginRequest);
		authenticationOpt.orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));
		Authentication authentication = authenticationOpt.get();

		logger.info("Logged in User returned [API]: " + authentication.getName());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
		Optional<User> registeredUserOpt = userService.registerUser(registrationRequest);
		registeredUserOpt.orElseThrow(() -> new UserRegistrationException("Couldn't register user [" + registrationRequest +
				"]"));
		User registeredUser = registeredUserOpt.get();

		logger.info("Registered User returned [API[: " + registeredUser);
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{email}")
				.buildAndExpand(registeredUser.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse("User registered successfully", true));
	}
}
