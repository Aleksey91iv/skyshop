package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.service.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ProductBasket productBasket;

    @Test
    void isThrowNoSuchProductException() {
        Repository mockRepository = Mockito.mock(Repository.class);

        Assertions.assertThrows(NoSuchProductException.class, () -> {
            new BasketService(productBasket, storageService).addProductItem(UUID.randomUUID());
        });
    }

    @Test
    void isCallAddProductOfProductBasket() {
        Repository mockRepository = Mockito.mock(Repository.class);

        UUID uuid = UUID.randomUUID();
        SimpleProduct simpleProduct = new SimpleProduct(uuid, "Сандалеты Размер 42", 100);

        Optional<Product> optionalProduct = Optional.of(simpleProduct);
        Mockito.doReturn(optionalProduct).when(storageService).getProductById(uuid);

        BasketService basketService = new BasketService(productBasket, storageService);
        basketService.addProductItem(uuid);

        Mockito.verify(productBasket).add(uuid);
    }

    @Test
    void getUserBasketIsEmpty() {
        Repository mockRepository = Mockito.mock(Repository.class);

        Mockito.doReturn(new HashMap<UUID, Integer>()).when(productBasket).getBasketProducts();

        BasketService basketService = new BasketService(productBasket, storageService);
        UserBasket userBasket = basketService.getUserBasket();

        Assertions.assertTrue(userBasket != null
            && userBasket.getBasketItems().isEmpty());
    }

    @Test
    void getUserBasketIsNotEmpty() {
        Repository mockRepository = Mockito.mock(Repository.class);

        Map<UUID, Integer> productsBasketMock = new HashMap<>();
        UUID uuid1 = UUID.randomUUID();
        SimpleProduct simpleProduct1 = new SimpleProduct(uuid1, "Сандалеты Размер 42", 100);
        Optional<Product> optionalProduct1 = Optional.of(simpleProduct1);
        UUID uuid2 = UUID.randomUUID();
        SimpleProduct simpleProduct2 = new SimpleProduct(uuid2, "Енот", 1000);
        Optional<Product> optionalProduct2 = Optional.of(simpleProduct2);
        productsBasketMock.put(uuid1, 3);
        productsBasketMock.put(uuid2, 1);

        Mockito.doReturn(productsBasketMock).when(productBasket).getBasketProducts();
        Mockito.doReturn(optionalProduct1).when(storageService).getProductById(uuid1);
        Mockito.doReturn(optionalProduct2).when(storageService).getProductById(uuid2);

        BasketService basketService = new BasketService(productBasket, storageService);
        UserBasket userBasket = basketService.getUserBasket();

        Assertions.assertTrue(userBasket != null
            && userBasket.getBasketItems()
                .stream()
                .filter(bi -> bi.getProduct().getId() == simpleProduct1.getId()
                    && bi.getCount() == 3).count() == 1
            && userBasket.getBasketItems()
                .stream()
                .filter(bi -> bi.getProduct().getId() == simpleProduct2.getId()
                    && bi.getCount() == 1).count() == 1);
    }
}
