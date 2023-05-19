package com.customer.backend.service;

import com.customer.backend.exception.NotEnoughProductsInStockException;
import com.customer.backend.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Long, Product> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
