package org.skypro.skyshop.exception.service;

import org.skypro.skyshop.model.error.ShopError;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {

    private final ShopError error;

    public NoSuchProductException(UUID productId) {
        super("Не найден товар c id=" + productId);
        this.error = new ShopError(1, getMessage());
    }

    public ShopError getShopError() {
        return error;
    }
}
