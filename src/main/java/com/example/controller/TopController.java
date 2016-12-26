package com.example.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.User;

@Controller
public class TopController {
	
	@RequestMapping(value = "/", method = {RequestMethod.GET} )
	public String index(Model model){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User)principal;
		
		model.addAttribute("user", user);
		return "index";
	}
	
}
