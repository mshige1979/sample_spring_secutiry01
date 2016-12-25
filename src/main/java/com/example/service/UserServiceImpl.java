package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Component
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("");
		}
		
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("");
		}
		
		return user;
	}

}
