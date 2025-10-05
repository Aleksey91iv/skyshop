package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.*;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private String name;

    public Product(UUID id, String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Некорректное именование продукта.");
        }
        this.name = name;
        this.id = id;
    }

    public UUID getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    public String getSearchTerm() {
        return name;
    }

    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Product product ? name.equals(product.name) : false;
    }
}
