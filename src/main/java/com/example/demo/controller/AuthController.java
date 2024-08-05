package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {
	@GetMapping("/customers")
	public String index(Model model) {
		model.addAttribute("isLogged", null); // TODO: ログイン認証処理実装までの暫定措置
		return "login";
	}
	
}
