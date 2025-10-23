package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> basket;

    public ProductBasket() {
        basket = new HashMap<>();
    }

    public void add(UUID productId) {

        basket.merge(productId, 1, (prev, value) -> prev + value);
    }

    public Map<UUID, Integer> getBasketProducts() {
        return Collections.unmodifiableMap(basket);
    }
}
