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

        if (basket.containsKey(productId))
        {
            int currentValue = basket.get(productId);
            basket.put(productId, currentValue + 1);
        } else {
            basket.put(productId, 1);
        }
    }

    public Map<UUID, Integer> getBasketProducts() {
        return Collections.unmodifiableMap(basket);
    }
}
