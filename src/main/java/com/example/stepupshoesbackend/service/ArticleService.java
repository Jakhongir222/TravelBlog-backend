package com.example.stepupshoesbackend.service;

import com.example.stepupshoesbackend.model.Article;
import com.example.stepupshoesbackend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository repository;

    public Object getAllArticles() {
        return repository.findAll();
    }

    public Object getSpecificArticle(Long articleId) {
        return repository.findById(articleId);
    }

    public Article createArticle(Article article) {
        return repository.save(article);
    }


    public void deleteArticle(Long articleId) {
         repository.deleteById(articleId);
    }
}
