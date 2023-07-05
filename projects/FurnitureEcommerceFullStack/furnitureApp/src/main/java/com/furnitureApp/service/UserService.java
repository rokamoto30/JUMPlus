package com.furnitureApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.User;
import com.furnitureApp.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    // public User createUser(User user) {
    //     user.setId(null);
    //     User created = repo.save(user);
    //     return created;
    // }

    // public User getUserById(int id) throws InvalidException {
    //     Optional<User> found = repo.findById(id);
    //     if(found.isEmpty()) {
    //         throw new InvalidException("No user found");
    //     }
    //     return found.get();
    // }

    // public User updateUser(User user) throws InvalidException {
    //     if(repo.existsById(user.getId())) {
    //         return repo.save(user);
    //     }
    //     throw new InvalidException("No user found");
    // }
    
}
