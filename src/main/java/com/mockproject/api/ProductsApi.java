package com.mockproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.entity.Products;
import com.mockproject.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsApi {
	
	//Su dung Service
	@Autowired
	private ProductsService productsService;
	
	//ENDPOINT: localhost:8080/api/products/
	@GetMapping("/") //getAll products
	public ResponseEntity<?> doGetAll(){
		List<Products> products = productsService.findAll();
		return ResponseEntity.ok(products);
		
		/* return không trả về data.
		  return new ResponseEntity(HttpStatus.OK);
		*/
	}
	
}


/*
 * API khi trả về phải có 2 yếu tố sau:
 * 1. Data: có thể có hoặc không
 * 2. Http status: 200, 201, 202 ...
 */