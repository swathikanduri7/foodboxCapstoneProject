package com.example.polls.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.polls.exception.AppException;
import com.example.polls.model.Recipe;
import com.example.polls.model.Role;
import com.example.polls.model.RoleName;
import com.example.polls.model.User;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.JwtAuthenticationResponse;
import com.example.polls.payload.LoginRequest;
import com.example.polls.payload.ResponseOutput;
import com.example.polls.payload.SignUpRequest;
import com.example.polls.payload.UserResponse;
import com.example.polls.repository.RoleRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.JwtTokenProvider;
import com.example.polls.security.UserPrincipal;

@RestController
// @RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(
			@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean admin = false;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			User user = userRepository.findByUsernameOrEmail(currentUserName,
					currentUserName).orElseThrow(
					() -> new UsernameNotFoundException(
							"User not found with username or email : "
									+ currentUserName));

			Set<Role> roles = new HashSet<>();
			for (Role role : user.getRoles()) {
				if (role.getName().toString().contains("ADMIN")) {
					admin = true;
				}
			}

		}
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, admin));
	}

	@GetMapping("/check")
	public ResponseEntity<?> validateUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// String currentUserName = authentication.getName();
			return ResponseEntity.ok(new ResponseOutput("All ok"));
		}
		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

	@GetMapping("/admin/check")
	public ResponseEntity<?> validateAdminUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// String currentUserName = authentication.getName();
			return ResponseEntity.ok(new ResponseOutput("All ok"));
		}
		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

	@GetMapping("/read")
	public ResponseEntity<?> getUser(@CurrentUser UserPrincipal currentUser) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			User user = userRepository.findByUsernameOrEmail(
					currentUser.getUsername(), currentUser.getEmail())
					.orElseThrow(
							() -> new UsernameNotFoundException(
									"User not found with username or email : "
											+ currentUserName));
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setName(user.getName());
			userResponse.setEmail(user.getEmail());
			userResponse.setPassword(user.getPassword());
			String roleName = "";
			Set<Role> roles = new HashSet<>();
			for (Role role : user.getRoles()) {
				roleName = role.getName().toString();
			}
			if (roleName.contains("ADMIN")) {
				userResponse.setRole("admin");
			}

			if (roleName.contains("USER")) {
				userResponse.setRole("customer");
			}

			userResponse.setContact(user.getContact());

			return ResponseEntity.ok(new ResponseOutput(userResponse));
		}
		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

	@GetMapping("/admin/getalluser")
	public ResponseEntity<?> getUsers() {
		List<UserResponse> usersList = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setName(user.getName());
			userResponse.setEmail(user.getEmail());
			userResponse.setPassword(user.getPassword());
			String roleName = "";
			Set<Role> roles = new HashSet<>();
			for (Role role : user.getRoles()) {
				roleName = role.getName().toString();
			}
			if (roleName.contains("ADMIN")) {
				userResponse.setRole("admin");
			}

			if (roleName.contains("USER")) {
				userResponse.setRole("customer");
			}

			userResponse.setContact(user.getContact());
			usersList.add(userResponse);
		}
		return ResponseEntity.ok(new ResponseOutput(usersList));
	}

	@DeleteMapping("/admin/blockuser/{userId}")
	public ResponseEntity<?> blockuser(@PathVariable String userId) {

		// Creating user's account
		User user = userRepository.getById(Long.valueOf(userId));
		user.setBlocked(Boolean.TRUE);
		userRepository.save(user);

		return ResponseEntity.ok(new ResponseOutput("All ok"));
	}

	@DeleteMapping("/admin/unblockuser/{userId}")
	public ResponseEntity<?> unblockuser(@PathVariable String userId) {

		// Creating user's account
		User user = userRepository.getById(Long.valueOf(userId));
		user.setBlocked(Boolean.FALSE);
		userRepository.save(user);

		return ResponseEntity.ok(new ResponseOutput("All ok"));
	}

	@DeleteMapping("/admin/deleteuser/{userId}")
	public ResponseEntity<?> deleteuser(@PathVariable String userId) {

		// Creating user's account
		User user = userRepository.getById(Long.valueOf(userId));
		// user.setBlocked(Boolean.FALSE);
		userRepository.delete(user);

		return ResponseEntity.ok(new ResponseOutput("All ok"));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(
			@Valid @RequestBody SignUpRequest signUpRequest) {

		if (!signUpRequest.getP1().equals(signUpRequest.getP2())) {
			return new ResponseEntity(new ApiResponse(false,
					"Passwords not matched!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByUsername(signUpRequest.getName())) {
			return new ResponseEntity(new ApiResponse(false,
					"Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false,
					"Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getName(),
				signUpRequest.getEmail(), signUpRequest.getP1(),
				signUpRequest.getContact());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/users/{username}").buildAndExpand(result.getUsername())
				.toUri();

		return ResponseEntity.created(location).body(
				new ApiResponse(true, "User registered successfully"));
	}
}
