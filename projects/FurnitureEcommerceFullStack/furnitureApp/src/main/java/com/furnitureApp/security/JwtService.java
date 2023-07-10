package com.furnitureApp.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.furnitureApp.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "FYiY9Jt5LD8H5fzsnEYsEZf7+ps2/Jrya8n6l7VgWNE+2s36s1e/IshG3kC6Z7il";
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExperation(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExperation(token).before(new Date());
	}
	
//	public String extractUsername(String token) {
//		return extractClaims(token, Claims::getSubject);
//	}
	
	public String generateToken(User user) { // add roles in token
		Map<String, Object> roles = new HashMap<>();
		roles.put("role", user.getRole());
		return generateToken(roles, user);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
	public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt( new Date( System.currentTimeMillis() ) )
				.setExpiration( new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 24 ) )
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) { //take token and method to extract wanted claim
		final Claims claims = extractAllClaims(token); //all claims
		return claimsResolver.apply(claims); //apply passed function to get specific claim
	}
	
	private Claims extractAllClaims(String token) { // get all claims
		return Jwts.parserBuilder()				
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Key getSignInKey() {
		byte[] byteKey = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(byteKey);
	}
}
