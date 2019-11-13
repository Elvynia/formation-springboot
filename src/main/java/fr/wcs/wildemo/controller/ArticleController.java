package fr.wcs.wildemo.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.wcs.wildemo.entity.Account;
import fr.wcs.wildemo.entity.Article;
import fr.wcs.wildemo.service.AccountService;
import fr.wcs.wildemo.service.ArticleService;

@Controller
// Défini 'account' en tant qu'attribut du model à conserver pour chaque client/utilisateur séparément.
@SessionAttributes({ "authorId", "lastArticle" })
// Pour séparer les attributs en session, il faut une instance de ArticleController pour chaque client/utilisateur.
@Scope("session")
public class ArticleController {

	/**
	 * Accès aux traitements des comptes.
	 */
	@Autowired
	private AccountService accountService;

	/**
	 * Accès aux traitements des articles.
	 */
	@Autowired
	private ArticleService service;

	/**
	 * @return Account Renvoie la valeur initiale de l'attribut de session
	 *         'account'.
	 */
	@ModelAttribute("authorId")
	public Integer account() {
		return null;
	}

	@ModelAttribute("lastArticle")
	public Article testObj() {
		return null;
	}

	@GetMapping("/")
	public String index(Model model,
			@ModelAttribute("authorId") Integer authorId,
			Principal principal, HttpSession session) {
		// Si l'attribut de session est null, il n'existe pas encore.
		// Il faut lui donner une valeur en cherchant l'id en BDD à
		// partir de son login.
		if (authorId == null) {
			Account readByLogin = this.accountService
					.readByLogin(principal.getName());
			// 'authorId' sera rattaché à la session utilisateur et sera disponible
			// dans toutes les templates utilisées par ce contrôleur.
			model.addAttribute("authorId", readByLogin.getId());
		}
		model.addAttribute("articles", this.service.getAll());
		return "index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("article", new Article());
		return "form";
	}

	@PostMapping("/form")
	public String save(@Valid Article article, BindingResult result,
			@ModelAttribute("authorId") Integer authorId,
			@ModelAttribute("lastArticle") Article lastArticle,
			Model model) {
		// Récupération des attributs de session 'authorId' et 'lastArticle'.
		if (result.hasErrors()) {
			return "form";
		} else {
			Article dbArticle;
			if (article.getId() != null) {
				dbArticle = this.service.update(article.getId(),
						article.getTitle(), article.getContent());
			} else {
				dbArticle = this.service.create(authorId,
						article.getTitle(), article.getContent());
			}
			model.addAttribute("lastArticle", dbArticle);
			return "redirect:/";
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.service.delete(id);
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String edit(Model model, Integer id) {
		model.addAttribute("article", this.service.read(id));
		return "form";
	}
}
