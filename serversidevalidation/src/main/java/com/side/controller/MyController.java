package com.side.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.side.entitis.LoginData;

@Controller
public class MyController {
	
	
	@RequestMapping("/form")
	public String openForm(Model model) {
		
		System.out.println("open form");
		model.addAttribute("loginData", new LoginData());
		return "form";
	}

	@PostMapping("/process")
	public String process(@ModelAttribute("loginData") LoginData loginData) {
		
		System.out.println(loginData);
		
		return "succes";
	}
}
