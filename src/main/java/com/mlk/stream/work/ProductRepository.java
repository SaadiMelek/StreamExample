package com.mlk.stream.work;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByName(String name);
	// Test spring data queries based on naming convention
	List<Product> findDistinctByIdIsNotNull();
}
