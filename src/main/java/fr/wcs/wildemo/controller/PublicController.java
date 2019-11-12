package fr.wcs.wildemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {

	@GetMapping("/contact")
	public String contact() {
		return "public/contact";
	}

	@GetMapping("/account/create")
	public String createAccount() {
		return "public/createAccount";
	}
}
