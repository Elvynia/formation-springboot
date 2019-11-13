package fr.wcs.wildemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.wcs.wildemo.entity.Article;
import fr.wcs.wildemo.repository.AccountRepository;
import fr.wcs.wildemo.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository repository;

	@Autowired
	private AccountRepository accountRepository;

	public List<Article> getAll() {
		return this.repository.findAll();
	}

	public Article create(Integer accountId, String title,
			String content) {
		Article article = new Article(title, content);
		article.setAccount(this.accountRepository.getOne(accountId));
		return this.repository.save(article);
	}

	public Article read(int id) {
		return this.repository.getOne(id);
	}

	public Article update(Integer id, String title, String content) {
		Article article = this.read(id);
		article.setTitle(title);
		article.setContent(content);
		return this.repository.save(article);
	}

	public void delete(int id) {
		this.repository.deleteById(id);
	}

}
