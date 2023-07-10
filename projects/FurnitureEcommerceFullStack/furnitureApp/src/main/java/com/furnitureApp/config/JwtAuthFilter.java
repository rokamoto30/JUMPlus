package com.furnitureApp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.furnitureApp.model.User;
import com.furnitureApp.security.JwtService;
import com.furnitureApp.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	private final String bearer = "Bearer";
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String username;
		
		if (authHeader == null || !authHeader.startsWith(bearer)) { // ig header is missing or incorrect pass on to normal filter
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(bearer.length()); // set jwt string
		username = jwtService.extractUsername(jwt); // set username
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // if username is set and context holder is not filled
			User user = userService.getUserByUsername(username); // get user
			if (jwtService.isTokenValid(jwt, user)) { // check if the token is valid
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); //create auth token
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //add source details
				SecurityContextHolder.getContext().setAuthentication(authToken); //update security context holder
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
