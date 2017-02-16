package com.enterprise.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.enterprise.data.entities.Product;
import com.enterprise.data.repositories.ProductRepository;
import com.querydsl.core.types.Predicate;


@RestController
public class ProductSearchController {
	
	@Autowired
    private ProductRepository productRepository;
 
	@RequestMapping(method = RequestMethod.GET, value = "/api/products/search")
	public ResponseEntity<Page<Product>> searchProduct(     
	          Pageable pageable, @RequestParam MultiValueMap<String, String> parameters,
	          PagedResourcesAssembler<Product> assembler, @QuerydslPredicate(root = Product.class) Predicate predicate){
		Page<Product> products = productRepository.findAll(predicate, pageable);
		return new ResponseEntity<>(products ,HttpStatus.OK);
		
	}

}
