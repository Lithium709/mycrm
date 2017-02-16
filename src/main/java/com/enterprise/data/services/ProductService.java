package com.enterprise.data.services;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enterprise.data.dto.ProductDTO;
import com.enterprise.data.entities.Product;

public interface ProductService {

	Page<Product> findAll(Pageable pageable);

	List<ProductDTO> findAllHierarchy();
}
