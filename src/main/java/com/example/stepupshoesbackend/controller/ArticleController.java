package com.example.stepupshoesbackend.controller;

import com.example.stepupshoesbackend.model.Article;
import com.example.stepupshoesbackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService service;

    @GetMapping
    ResponseEntity<?> getAllArticles(){
        return ResponseEntity.ok(service.getAllArticles());
    }

    @GetMapping("/{articleId}")
    ResponseEntity<?> getSpecificArticle(@PathVariable Long articleId){
        return ResponseEntity.ok(service.getSpecificArticle(articleId));
    }

    @PostMapping()
    ResponseEntity<?> createArticle(@RequestBody Article article){
        if( article== null){
            throw new IllegalArgumentException("Article can not be empty");
        }
        Article savedArticle = service.createArticle(article);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticle.getArticleId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{articleId}")
    ResponseEntity<?> deleteArticle(@PathVariable Long articleId){
        service.deleteArticle(articleId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{articleId}")
    ResponseEntity<?> updateArticle(@PathVariable Long articleId, @RequestBody Article article){
        Optional<Article> existingArticle = (Optional<Article>) service.getSpecificArticle(articleId);
        if(!existingArticle.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Article articles = existingArticle.get();
        articles.setArticleText(article.getArticleText());
        articles.setArticleTitle(article.getArticleTitle());
        service.updateArticle(article);
        return ResponseEntity.ok().build();
    }
}
