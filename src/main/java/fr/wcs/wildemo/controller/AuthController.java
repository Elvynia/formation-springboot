package fr.wcs.wildemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/disconnected")
	public String disconnected(SessionStatus sessionStatus) {
		// Lorsque l'utilisateur se déconnecte, on doit indiquer
		// à Spring que les attributs de sessions doivent être
		// supprimés. De cette manière il n'y aura pas de conflits
		// avec les attributs de sa prochaine session.
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
