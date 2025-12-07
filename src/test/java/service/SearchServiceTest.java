package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.stereotype.Repository;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void whenProductsIsEmpty() {;
        Repository mockRepository = Mockito.mock(Repository.class);

        Mockito.doReturn(new HashSet<Searchable>()).when(storageService).getSearchables();
        Collection<SearchResult> result = searchService.search("Сандалеты");
        Assertions.assertTrue(result != null && result.isEmpty());
    }

    @Test
    void whenProductsIsNotFoundMatch() {
        Repository mockRepository = Mockito.mock(Repository.class);

        Product simpleProduct = new SimpleProduct(UUID.randomUUID(), "Сандалеты Размер 42", 100);
        HashSet<Searchable> searchables = new HashSet<>();
        searchables.add(simpleProduct);

        Mockito.doReturn(searchables)
            .when(storageService)
            .getSearchables();

        Collection<SearchResult> result = searchService.search("Арбуз");

        Assertions.assertTrue(result != null && result.isEmpty());
    }

    @Test
    void whenProductsIsMatch() {
        Repository mockRepository = Mockito.mock(Repository.class);

        Product simpleProduct = new SimpleProduct(UUID.randomUUID(), "Сандалеты Размер 42", 100);
        HashSet<Searchable> searchables = new HashSet<>();
        searchables.add(simpleProduct);

        Mockito.doReturn(searchables)
            .when(storageService)
            .getSearchables();

        Collection<SearchResult> result = searchService.search("Сандалеты");

        Assertions.assertTrue(result != null
            && result.stream().anyMatch(i -> i.getId() == simpleProduct.getId()));
    }
}
