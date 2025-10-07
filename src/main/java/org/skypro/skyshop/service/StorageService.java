package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {

        products = new HashMap<>();
        articles = new HashMap<>();

        Product simpleProduct = new SimpleProduct(UUID.randomUUID(), "Сандалеты Размер 42", 100);
        Product simpleProduct1 = new SimpleProduct(UUID.randomUUID(), "Сандалеты Размер 42", 105);
        Product discountedProduct = new DiscountedProduct(UUID.randomUUID(), "Сандалеты Размер 47", 200, 50);
        Product fixPriceProduct = new FixPriceProduct(UUID.randomUUID(), "Шлёпки");
        Product simpleProduct2 = new SimpleProduct(UUID.randomUUID(), "Вилла", 100000);

        Article sandalety = new Article(UUID.randomUUID(), "Сандалеты.", "Удобный товар.");
        Article manShlepkiArticle = new Article(UUID.randomUUID(), "Шлёпки", "Отличный товар товар.");
        Article womanShlepkiArticle = new Article(UUID.randomUUID(), "Шлёпки", "Хороший товар товар.");

        products.put(simpleProduct.getId(), simpleProduct);
        products.put(simpleProduct1.getId(), simpleProduct1);
        products.put(discountedProduct.getId(), discountedProduct);
        products.put(fixPriceProduct.getId(), fixPriceProduct);
        products.put(simpleProduct2.getId(), simpleProduct2);

        articles.put(sandalety.getId(), sandalety);
        articles.put(manShlepkiArticle.getId(), manShlepkiArticle);
        articles.put(womanShlepkiArticle.getId(), womanShlepkiArticle);

    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getSearchables() {

        Stream<Searchable> productStream = products.values()
            .stream()
            .map(product -> {
                return (Searchable)product;});

        Stream<Searchable> articleStream = articles.values()
                .stream()
                .map(article -> {
                    return (Searchable)article;});

        return Stream.concat(productStream, articleStream).collect(Collectors.toSet());
    }
}
