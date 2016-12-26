package com.example.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Controller
public class ErrorController {
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String forbidden() {
		return "errorpage/403";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound() {
		return "errorpage/404";
	}
	
}
