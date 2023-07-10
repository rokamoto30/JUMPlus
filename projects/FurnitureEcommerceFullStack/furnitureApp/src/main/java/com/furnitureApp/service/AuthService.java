package com.furnitureApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.furnitureApp.dto.loginDto;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.User;
import com.furnitureApp.repo.UserRepo;
import com.furnitureApp.security.JwtService;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
	@Autowired
    UserRepo repo;
    
    @Autowired
    JwtService jwtService;
    
    @Autowired
    AuthenticationManager authManager;
    
    @Transactional
	public String register(User user) {
   	 user.setId(null);
   	 User created = repo.save(user);
   	 String jwtToken = jwtService.generateToken(user);
   	 return jwtToken;
    }
    
    public String auth(loginDto creds) throws InvalidException {
   	 authManager.authenticate(
   			 new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword())
   			 );
   	 Optional<User> found = repo.findByUsername(creds.getUsername());
   	 if (found.isEmpty()) {
   		 throw new InvalidException("User not found");
   	 }
   	 User user = found.get();
   	 return jwtService.generateToken(user);
    }
}
