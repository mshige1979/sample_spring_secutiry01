package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin")
    public String index()
    {
        return "admin";
    }
	
	@RequestMapping(value = "/admin/sample1")
    public String sample1()
    {
        return "admin2";
    }
	
}
