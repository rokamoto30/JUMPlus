package com.furnitureApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.furnitureApp.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationProvider authProvider;
	
//	@Autowired
//	AuthenticationManager authManager;


    @Bean
    protected UserDetailsService userDetailService() {
    	
    	UserDetails user = User.withDefaultPasswordEncoder()
    			.username("user1")
    			.password("password")
    			.roles("USER")
    			.build();
    	
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        	.authorizeHttpRequests()
        	.requestMatchers("/").permitAll() 
        	.requestMatchers("/swagger-ui").permitAll() 
	        .requestMatchers("/swagger-ui/**").permitAll() 
	        .requestMatchers("/openapi.html/**").permitAll()
	        .requestMatchers("/authenticate").permitAll()
	    	.requestMatchers("/register").permitAll()
	    	.requestMatchers("/checkout/**").hasRole("USER")
	    	.requestMatchers("/product/create").hasRole("ADMIN")
	    	.requestMatchers("/product/edit").hasRole("ADMIN")
	    	.requestMatchers("/stock/edit").hasRole("ADMIN")
	    	.anyRequest().authenticated()
    	.and()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
//    	.authenticationProvider(authProvider)
    	.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
//    @Bean
//	protected DaoAuthenticationProvider authenticationProvider() {
//		
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder( encoder() );
//		
//		return authProvider;
//	}
    
//  @Bean
//  public AuthenticationProvider authenticationProvider() {
//	   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	   authProvider.setUserDetailsService(userService);
//	   authProvider.setPasswordEncoder( NoOpPasswordEncoder.getInstance());
//	   return authProvider;
//  }
//
//    @Bean
//	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//		return authConfig.getAuthenticationManager();
//	}
}
