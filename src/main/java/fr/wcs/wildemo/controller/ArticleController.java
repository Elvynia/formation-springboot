package fr.wcs.wildemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.wcs.wildemo.entity.Article;
import fr.wcs.wildemo.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("articles", this.service.getAll());
		return "index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("article", new Article());
		return "form";
	}

	@PostMapping("/form")
	public String save(@Valid Article article, BindingResult result) {
		if (result.hasErrors()) {
			return "form";
		} else {
			if (article.getId() != null) {
				this.service.update(new Article(article.getId(),
						article.getTitle(), article.getContent()));
			} else {
				service.create(article.getTitle(), article.getContent());
			}
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
