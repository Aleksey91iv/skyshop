package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.*;
import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final UUID id;
    private String title;
    private String content;

    public Article ( UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public UUID getId(){
        return id;
    }

    public String getName() {
        return title;
    }

    public String getSearchTerm() {
        return title + content;
    }

    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + ": " + content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Article article ? title.equals(article.title) : false;
    }
}