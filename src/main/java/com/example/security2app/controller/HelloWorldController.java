package com.example.security2app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "home";
	}
	
	@RequestMapping("/hello-world")
	@ResponseBody
	public String helloworld() {
		return "hello world";
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public String adminPage() {
		return "hello, admin";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public String userPage() {
		return "hello, user";
	}
}
