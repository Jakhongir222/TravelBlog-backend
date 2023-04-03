package com.example.stepupshoesbackend.controller;

import com.example.stepupshoesbackend.model.Article;
import com.example.stepupshoesbackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

}
