package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long>{
	
	 
	
}
