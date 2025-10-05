package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class StorageController {

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return new StorageService().getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return new StorageService().getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> searchResults(String pattern) {
        return new SearchService().search(pattern);
    }
}
