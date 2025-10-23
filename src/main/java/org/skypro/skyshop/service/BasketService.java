package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {

        this.productBasket = productBasket;
        this.storageService = storageService;
    }

     public void addProductItem(UUID id) {

        storageService.getProductById(id)
            .orElseThrow(() -> new IllegalArgumentException("Нет позиции товара с id=" + id));

        productBasket.add(id);
     }

     public UserBasket getUserBasket() {

         ArrayList<BasketItem> basketItems = new ArrayList<>();
         productBasket.getBasketProducts()
             .keySet()
             .stream()
             .filter(Objects::nonNull )
             .forEach(item ->
             {
                 Optional<Product> optionalProduct = storageService.getProductById(item);
                 optionalProduct.ifPresent(product ->
                     basketItems.add(new BasketItem(product, productBasket.getBasketProducts().get(item))));
             });

         return new UserBasket(basketItems);
     }
}
