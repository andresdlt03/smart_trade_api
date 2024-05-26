package com.bluejtitans.smarttradebackend;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bluejtitans.smarttradebackend.lists.repository.ProductListRepository;
import com.bluejtitans.smarttradebackend.users.controller.http.login.LoginCredentials;
import com.bluejtitans.smarttradebackend.users.controller.http.login.LoginFailed;
import com.bluejtitans.smarttradebackend.users.controller.http.login.LoginRequest;
import com.bluejtitans.smarttradebackend.users.controller.http.login.LoginResponse;
import com.bluejtitans.smarttradebackend.users.controller.http.login.LoginSuccess;
import com.bluejtitans.smarttradebackend.users.controller.http.register.RegisterFailed;
import com.bluejtitans.smarttradebackend.users.controller.http.register.RegisterResponse;
import com.bluejtitans.smarttradebackend.users.controller.http.register.RegisterSuccess;
import com.bluejtitans.smarttradebackend.users.model.Client;
import com.bluejtitans.smarttradebackend.users.model.User;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;
import com.bluejtitans.smarttradebackend.users.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private ProductListRepository productListRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveUser_Success() {
		User user = new Client();
		user.setEmail("test@example.com");

		when(userRepository.existsById(user.getEmail())).thenReturn(false);
		when(userRepository.save(user)).thenReturn(user);

		ResponseEntity<RegisterResponse> response = userService.saveUser(user);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertTrue(response.getBody() instanceof RegisterSuccess);
		verify(userRepository, times(1)).existsById(user.getEmail());
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testSaveUser_UserAlreadyExists() {
		User user = new Client();
		user.setEmail("test@example.com");

		when(userRepository.existsById(user.getEmail())).thenReturn(true);

		ResponseEntity<RegisterResponse> response = userService.saveUser(user);

		assertTrue(response.getStatusCode().is4xxClientError());
		assertTrue(response.getBody() instanceof RegisterFailed);
		verify(userRepository, times(1)).existsById(user.getEmail());
		verify(userRepository, never()).save(user);
	}

	@Test
	void testLoginUser_Success() {
		LoginRequest loginRequest = new LoginRequest();
		LoginCredentials credentials = new LoginCredentials();
		credentials.setEmail("test@example.com");
		credentials.setPassword("password");
		loginRequest.setCredentials(credentials);

		User user = new Client();
		user.setEmail("test@example.com");
		user.setPassword("password");

		when(userRepository.findById(credentials.getEmail())).thenReturn(Optional.of(user));

		ResponseEntity<LoginResponse> response = userService.loginUser(loginRequest);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertTrue(response.getBody() instanceof LoginSuccess);
		verify(userRepository, times(1)).findById(credentials.getEmail());
	}

	@Test
	void testLoginUser_InvalidCredentials() {
		LoginRequest loginRequest = new LoginRequest();
		LoginCredentials credentials = new LoginCredentials();
		credentials.setEmail("test@example.com");
		credentials.setPassword("wrongPassword");
		loginRequest.setCredentials(credentials);

		User user = new Client();
		user.setEmail("test@example.com");
		user.setPassword("password");

		when(userRepository.findById(credentials.getEmail())).thenReturn(Optional.of(user));

		ResponseEntity<LoginResponse> response = userService.loginUser(loginRequest);

		assertTrue(response.getStatusCode().is4xxClientError());
		assertTrue(response.getBody() instanceof LoginFailed);
		verify(userRepository, times(1)).findById(credentials.getEmail());
	}
}