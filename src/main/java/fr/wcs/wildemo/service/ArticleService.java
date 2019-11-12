package fr.wcs.wildemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.wcs.wildemo.entity.Article;
import fr.wcs.wildemo.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository repository;

	public List<Article> getAll() {
		return this.repository.findAll();
	}

	public void create(String title, String content) {
		this.repository.save(new Article(title, content));
	}

	public Article read(int id) {
		return this.repository.getOne(id);
	}

	public void update(Article article) {
		this.repository.save(article);
	}

	public void delete(int id) {
		this.repository.deleteById(id);
	}

}
