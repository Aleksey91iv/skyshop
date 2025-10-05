package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discount;

    public DiscountedProduct (UUID id, String name, int basePrice, int discount) {
        super(id, name);

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Некорректная цена. Стоимость товара должна быть строго больше 0.");
        }
        this.basePrice = basePrice;

        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Некорректная скидка. Допустимый диапозон скидки от 0 до 100.");
        }
        this.discount = discount;
    }

    @Override
    public int getPrice(){
        return basePrice - basePrice * discount / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.getName() + ": " + getPrice() + "(" + discount + "%)";
    }
}
