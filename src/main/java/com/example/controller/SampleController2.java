package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {
	
	@RequestMapping(value = "/user/sample1")
    public String sample1()
    {
        return "user/sample1";
    }
	
	@RequestMapping(value = "/user/sample2")
    public String sample2()
    {
        return "user/sample2";
    }
	
}
