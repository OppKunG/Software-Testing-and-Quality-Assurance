package com.customer.backend.service.Impl;

import com.customer.backend.exception.NotEnoughProductsInStockException;
import com.customer.backend.model.Product;
import com.customer.backend.repository.ProductRepository;
import com.customer.backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Shopping Cart is implemented with a Map, and as a session bean
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;

    private Map<Long,Product > products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {
        if(products.containsKey(product.getId())){
            products.remove(product.getId());

        }

    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Long, Product> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            // Refresh quantity for every product before checking

            Optional<Product> product2 = productRepository.findById(entry.getValue().getId());
            if(product2.isPresent()){
                product = product2.get();
                if (product.getQuantity() < entry.getKey())
                    throw new NotEnoughProductsInStockException(product);
                entry.getValue().setQuantity(product.getQuantity() - entry.getValue());
            }

        }
        productRepository.save(products.keySet());
        productRepository.flush();
        products.clear();
    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getValue().getPrice().multiply(BigDecimal.valueOf(entry.getValue().getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
