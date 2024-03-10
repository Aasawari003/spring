package com.firstPro.controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstPro.config.JwtProvider;
import com.firstPro.models.customerSignUp;
import com.firstPro.repository.customerRepository;
import com.firstPro.request.customerLoginRequest;
import com.firstPro.response.AuthResponse;
import com.firstPro.service.customerDetailsService;
import com.firstPro.service.customerService;

@RestController
@RequestMapping("/auth")
public class customerAuthController {
	
	@Autowired
private customerService customerService;
	@Autowired
	private customerRepository cRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private customerDetailsService customerDetailsService;
	
//	 /auth/customersignup

//	 for customer
	@PostMapping("/customersignup")
	public AuthResponse createCustomer(@RequestBody customerSignUp cUp) throws Exception {
	    // Check if the email is already used
	    customerSignUp isExist = cRepository.findByEmail(cUp.getEmail());
	    if (isExist != null) {
	        throw new Exception("This Email is already in use with another account");
	    }

	    // Create a new customerSignUp object with the provided details
	    customerSignUp newUser = new customerSignUp();
	    newUser.setUsername(cUp.getUsername());
	    newUser.setEmail(cUp.getEmail());
	    newUser.setPassword(passwordEncoder.encode(cUp.getPassword()));

	    // Save the new user to the database
	    customerSignUp savedUser = cRepository.save(newUser);

	    // Print the saved user details to the console
	    System.out.println("New user details: " + newUser);
	    // Create authentication token for the saved user
	    Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

	    // Generate JWT token
	    String token = JwtProvider.generateToken(authentication);

	    // Create and return the AuthResponse
	    AuthResponse res = new AuthResponse(token, "Registration successful");
	    return res;
	}


//	http://localhost:8080/auth/api/customersignin
	@PostMapping("/api/customersignin")
	public AuthResponse signin(@RequestBody customerLoginRequest customerLoginRequest) {
	    Authentication authentication = authenticate(customerLoginRequest.getEmail(), customerLoginRequest.getPassword());
	    
	    String token = JwtProvider.generateToken(authentication);
	    
	    AuthResponse res = new AuthResponse(token, "Login Successful");
	    return res;
	}


	private Authentication authenticate(String email, String password) {
		UserDetails userDetails= customerDetailsService.loadUserByUsername(email);
		if (userDetails==null) {
			throw new BadCredentialsException("Invalid Username");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
			//in above password = obtained through interface and  2nd arugment is password obtained through backend which will be encoded
		}
		return new UsernamePasswordAuthenticationToken(userDetails,
				null,
				userDetails.getAuthorities());
	}
}
