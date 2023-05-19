package com.customer.backend.service;

import com.customer.backend.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
}
