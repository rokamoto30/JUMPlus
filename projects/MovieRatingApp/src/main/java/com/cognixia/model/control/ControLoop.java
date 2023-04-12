package com.cognixia.model.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognixia.repo.MovieRepo;
import com.cognixia.repo.RatingRepo;
import com.cognixia.repo.UserRepo;

import jakarta.transaction.Transactional;

@Component
public class ControLoop {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private RatingRepo ratingRepo;
	
	@Transactional
	public void controLoop() {
//		System.out.println(userRepo);
//		System.out.println(movieRepo);
//		System.out.println(ratingRepo);
		System.out.println("user found is:");
		System.out.println(userRepo.getUser("Ryan"));

	}
	
	public ControLoop(){};

}
