package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);

        if (price <= 0) {
            throw new IllegalArgumentException("Некорректная цена. Стоимость товара должна быть строго больше 0.");
        }
        this.price = price;
    }

    @Override
    public int getPrice(){
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return super.getName() + ": " + getPrice();
    }
}
