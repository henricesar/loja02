package com.github.ericomonteiro.smartstock.repository;

import com.github.ericomonteiro.smartstock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
