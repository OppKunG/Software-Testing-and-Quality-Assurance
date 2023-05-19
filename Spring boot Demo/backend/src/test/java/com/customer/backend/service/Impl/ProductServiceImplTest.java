package com.customer.backend.service.Impl;

import com.customer.backend.model.Product;
import com.customer.backend.repository.ProductRepository;
import com.customer.backend.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void should_return_product_list_when_call_get_products_given_mockup_product() {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setName("product1");
        product.setQuantity(10);
        List<Product> productList = List.of(product);
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> actual = productServiceImpl.getProducts();

        // Then
        assertEquals(productList, actual);
    }

    @Test
    void should_return_empty_list_when_call_get_products_given_get_empty_list_from_repository() {
        // Given
        Product product = new Product();
        List<Product> productList = List.of(product);
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> actual = productServiceImpl.getProducts();

        // Then
        assertEquals(productList, actual);
    }

    @Test
    void should_return_product_id_when_call_find_by_id_given_get_product_id_list_from_repository() {
        // Given

        Product product = new Product();
        product.setId(1L);

        Optional<Product> optionalProduct = Optional.of(product);
        when(productRepository.findById(product.getId())).thenReturn(optionalProduct);

        // When
        Optional<Product> actual = productServiceImpl.findById(product.getId());

        // Then
        assertEquals(optionalProduct, actual);
    }
}