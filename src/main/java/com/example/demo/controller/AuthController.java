package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			Model model) {
		return "companies";
	}
	
	@GetMapping({"/", "/customers", "/customers/login"})
	public String index(Model model) {
		model.addAttribute("isLogged", null); // TODO: ログイン認証処理実装までの暫定措置
		return "login";
	}
	
}
