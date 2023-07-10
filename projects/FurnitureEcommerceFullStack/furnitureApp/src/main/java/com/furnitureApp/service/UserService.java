package com.furnitureApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.furnitureApp.dto.loginDto;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.User;
import com.furnitureApp.repo.UserRepo;
import com.furnitureApp.security.JwtService;

@Service
public class UserService {
    @Autowired
    UserRepo repo;
    
    

    // public User createUser(User user) {
    //     user.setId(null);
    //     User created = repo.save(user);
    //     return created;
    // }

     public User getUserById(int id) throws InvalidException {
         Optional<User> found = repo.findById(id);
         if(found.isEmpty()) {
             throw new InvalidException("No user found");
         }
         return found.get();
     }
     
     public User getUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> found = repo.findByUsername(username);
         if(found.isEmpty()) {
             throw new UsernameNotFoundException("No user found");
         }
         return found.get();
     }
     
     public User create(User user) {
    	 user.setId(null);
    	 User created = repo.save(user);
    	 return created;
     }
      

    // public User updateUser(User user) throws InvalidException {
    //     if(repo.existsById(user.getId())) {
    //         return repo.save(user);
    //     }
    //     throw new InvalidException("No user found");
    // }
    
}
