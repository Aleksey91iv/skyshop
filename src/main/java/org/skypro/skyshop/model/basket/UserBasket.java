package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;

public final class UserBasket {

    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(List<BasketItem> basketItems) {

        this.basketItems = basketItems;
        total = basketItems
            .stream()
            .filter(Objects::nonNull)
            .mapToInt(bi -> bi.getProduct().getPrice() * bi.getCount())
            .sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return total;
    }
}
